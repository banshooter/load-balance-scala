package com.jarunj.distribute.loadbalance

import org.scalatest._

class Server(override val context: String) extends MeasuredResource[String] {
  private lazy val measuredExecutioner = new MeasuredExecutioner {
    override def timeOut: Long = 7000
  }

  override protected def executioner: MeasuredExecutioner = measuredExecutioner
}

class IntCommand(value: Int) extends Command[Int, String] {
  override def execute(context: String): Int = value
}


class DelayedCommand(value: Int) extends Command[Int, String] {
  override def execute(context: String): Int = {
    Thread.sleep(value)
    value
  }
}

class LoadBalanceSpec extends FlatSpec with Matchers {
  "RoundRobinLoadBalance" should "do its work" in {
    val resources = Seq(
      new Server("server1"),
      new Server("server2")
    )
    val commands = Seq(
      new IntCommand(10),
      new IntCommand(11),
      new IntCommand(12),
      new IntCommand(13)
    )
    val rrl = new RoundRobinLoadBalance(resources)
    commands.foreach(rrl.employ)
    resources.forall(_.timesOfCalling == commands.size / resources.size) shouldEqual true
  }

  "WeightedRoundRobinLoadBalance" should "do its work" in {
    val resources = Seq(
      new Server("server1") {
        override def resourcefulness: Long = 2
      }, new Server("server2") {
        override def resourcefulness: Long = 1
      }
    )
    val commands = Seq(
      new IntCommand(10),
      new IntCommand(100),
      new IntCommand(10),
      new IntCommand(100),
      new IntCommand(100),
      new IntCommand(100)
    )
    val rrl = new WeightedRoundRobinLoadBalance(resources)
    commands.foreach(rrl.employ)
    resources.map(_.timesOfCalling).sum shouldEqual commands.size
    resources.forall(r => r.timesOfCalling / r.resourcefulness == 2)  shouldEqual true
  }

  "FastestResponseTimeLoadBalance" should "do its work" in {
    val resources = Seq(new Server("server1"), new Server("server2"), new Server("server3"))
    val frl = new FastResponseTimeLoadBalance(resources)
    frl.employ(new DelayedCommand(10))
    frl.employ(new DelayedCommand(100))
    frl.employ(new DelayedCommand(1000))
    resources.forall(_.timesOfCalling == 1) shouldEqual true
    frl.employ(new DelayedCommand(200))
    frl.employ(new DelayedCommand(200))
    frl.employ(new DelayedCommand(200))
    (resources(0).timesOfCalling + resources(1).timesOfCalling) shouldEqual 5
  }
}
