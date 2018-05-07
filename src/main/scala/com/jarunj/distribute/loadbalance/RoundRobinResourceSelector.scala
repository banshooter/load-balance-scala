package com.jarunj.distribute.loadbalance

class RoundRobinResourceSelector[C](override val resources: Seq[Resource[C]]) extends ResourceSelector[Resource[C]] {
  private var cursor: Int = -1

  override def select(): Resource[C] = {
    cursor = (cursor + 1) % resources.size
    resources(cursor)
  }
}
