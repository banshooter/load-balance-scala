package com.jarunj.distribute.loadbalance

trait ResourceSelector[R <: Resource[_]] {
  protected def resources: Seq[R]

  def select: R
}
