package com.jarunj.distribute.loadbalance

trait Command[T, Context] {
  def execute(context: Context): T
}
