package com.jarunj.distribute.loadbalance

class WeightedRoundRobinLoadBalance[Context](resources: Seq[Resource[Context]]) extends LoadBalance[Context, Resource[Context]] {
  lazy val roundRobinSelector = new WeightedRoundRobinResourceSelector(resources)
  override protected def selector: ResourceSelector[Resource[Context]] = roundRobinSelector
}
