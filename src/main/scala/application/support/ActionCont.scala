package application.support

import application.cont.ActionCont
import application.usecase.UseCaseResult

import scala.concurrent.Future
import scalaz.ContT

object ActionCont {
  def apply[A](f: (A => Future[UseCaseResult]) => Future[UseCaseResult]): ActionCont[A] = ContT(f)
}


