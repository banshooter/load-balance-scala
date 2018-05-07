package com.jarunj.distribute.loadbalance

class WeightedFastestResponseTimeResourceSelector[C](override val resources: Seq[MeasuredResource[C]]) extends ResourceSelector[MeasuredResource[C]] {
  override def select(): MeasuredResource[C] = {
    resources.minBy(r=> 1.0 * r.lastResponseTime / r.resourcefulness)
  }
}
