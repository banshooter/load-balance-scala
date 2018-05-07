package com.jarunj.distribute.loadbalance

class FastResponseTimeLoadBalance[Context](resources: Seq[MeasuredResource[Context]]) extends LoadBalance[Context, MeasuredResource[Context]] {
  lazy val fastestResponseTimeSelector = new FastestResponseTimeResourceSelector(resources)
  override protected def selector: ResourceSelector[MeasuredResource[Context]] = fastestResponseTimeSelector
}
