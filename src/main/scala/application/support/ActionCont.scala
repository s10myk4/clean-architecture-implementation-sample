package application.support

import application.cont.ActionCont
import application.usecase.UseCaseResult

import scala.concurrent.{ExecutionContext, Future}
import scalaz.ContT

object ActionCont {
  def apply[A](f: (A => Future[UseCaseResult]) => Future[UseCaseResult]): ActionCont[A] = ContT(f)

  def fromFuture[A](future: Future[A])(implicit ec: ExecutionContext): ActionCont[A] =
    ContT(future.flatMap(_))

  def successful[A](a: A)(implicit ec: ExecutionContext): ActionCont[A] =
    fromFuture(Future.successful(a))

  def failed[A](throwable: Throwable)(implicit ec: ExecutionContext): ActionCont[A] =
    fromFuture(Future.failed(throwable))
}