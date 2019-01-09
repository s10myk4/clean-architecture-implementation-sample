package com.s10myk4.application.usecase

trait Presenter[Arg, Out] {

  def present(arg: Arg): Out

}
