package com.jarunj.distribute.loadbalance

class WeightedRoundRobinResourceSelector[C](override val resources: Seq[Resource[C]]) extends ResourceSelector[Resource[C]] {
  override def select(): Resource[C] = {
    resources.minBy(r=> 1.0 * r.timesOfCalling / r.resourcefulness)
  }
}
