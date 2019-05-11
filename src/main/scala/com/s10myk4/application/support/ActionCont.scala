package com.s10myk4.application.support

import cats.data.ContT
import com.s10myk4.application.cont.ActionCont
import com.s10myk4.application.usecase.UseCaseResult

import scala.concurrent.{ExecutionContext, Future}
import scala.language.higherKinds

object ActionCont {
  def apply[F[_], A](f: (A => F[UseCaseResult]) => F[UseCaseResult]): ActionCont[F, A] = ContT(f)

  def point[F[_], A](a: => A): ActionCont[F, A] = ContT(f => f(a))

  //def liftM[M[_] : Monad, A](ma: M[A]): ActionCont[M, A] = ContT(f => Monad[M].point(ma)(f))

  def fromFuture[A](future: Future[A])(implicit ec: ExecutionContext): ActionCont[Future, A] =
    ContT(future.flatMap(_))

  def successful[A](a: A)(implicit ec: ExecutionContext): ActionCont[Future, A] =
    fromFuture(Future.successful(a))

  def failed[A](throwable: Throwable)(implicit ec: ExecutionContext): ActionCont[Future, A] =
    fromFuture(Future.failed(throwable))
}
