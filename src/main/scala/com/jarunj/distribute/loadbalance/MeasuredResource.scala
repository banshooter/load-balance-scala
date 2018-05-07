package com.jarunj.distribute.loadbalance

trait MeasuredResource[C] extends Resource[C] {
  final def lastResponseTime: Long = executioner.responseTime

  override protected def executioner: MeasuredExecutioner
}
