package com.jarunj.distribute.loadbalance

trait MeasuredExecutioner extends Executioner {
  def responseTime = elapsed

  def timeOut: Long

  private var start = 0L
  private var elapsed = 0L

  override protected def before {
    start = System.currentTimeMillis()
  }

  override protected def after {
    elapsed = System.currentTimeMillis() - start
  }

  override protected def exceptionHandler(executioner: Executioner, t: Throwable): Unit = {
    super.exceptionHandler(executioner, t)
    elapsed = timeOut
  }

}
