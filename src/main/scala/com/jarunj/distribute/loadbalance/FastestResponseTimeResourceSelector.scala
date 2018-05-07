package com.jarunj.distribute.loadbalance

class FastestResponseTimeResourceSelector[C](override val resources: Seq[MeasuredResource[C]]) extends ResourceSelector[MeasuredResource[C]] {
  override def select(): MeasuredResource[C] = {
    resources.minBy(_.lastResponseTime)
  }
}
