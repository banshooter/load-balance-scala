package com.jarunj.distribute.loadbalance

trait Resource[Context] {
  final def timesOfCalling = executioner.timesOfCalling

  def resourcefulness: Long = 1L

  def context: Context

  final def execute[T](command: Command[T, Context]): T = {
    executioner.execute(() => {
      command.execute(context)
    })
  }

  protected def executioner: Executioner
}
