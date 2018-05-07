package com.jarunj.distribute.loadbalance

import scala.annotation.tailrec
trait LoadBalance[Context, R <: Resource[Context]] {
  def maxRetry = 1

  private var retryTimes = 0

  protected def selector: ResourceSelector[R]

  @tailrec
  final def employ[T](command: Command[T, Context]): T = {
    try {
      selector.select.execute(command)
    } catch {
      case t: Throwable =>
        retryTimes = retryTimes + 1
        if (retryTimes >= maxRetry) throw t
        employ(command)
    }
  }
}
