package com.jarunj.distribute.loadbalance

class RoundRobinLoadBalance[Context](resources: Seq[Resource[Context]]) extends LoadBalance[Context, Resource[Context]] {
  lazy val roundRobinSelector = new RoundRobinResourceSelector(resources)
  override protected def selector: ResourceSelector[Resource[Context]] = roundRobinSelector
}
