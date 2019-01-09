package application.usecase

trait Presenter[Arg, Out] {

  def present(arg: Arg): Out

}
