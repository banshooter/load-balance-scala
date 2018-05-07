package com.jarunj.distribute.loadbalance

import scala.util.Try

trait Executioner {
  final def execute[T](fn: () => T): T = {
    try {
      before
      calledTimes = calledTimes + 1
      val result = fn()
      after
      result
    } catch {
      case t: Throwable =>
        Try(exceptionHandler(this, t))
        throw t
    }
  }

  private var calledTimes = 0L

  final def timesOfCalling = calledTimes

  protected def before

  protected def after

  protected def exceptionHandler(executioner: Executioner, t: Throwable) = t.printStackTrace()

}
