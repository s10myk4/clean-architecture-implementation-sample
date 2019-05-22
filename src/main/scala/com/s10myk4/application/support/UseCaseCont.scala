package com.s10myk4.application.support

import cats.data.ContT
import cats.effect.IO
import com.s10myk4.application.cont.UseCaseCont
import com.s10myk4.application.usecase.UseCaseResult

import scala.concurrent.{ExecutionContext, Future}
import scala.language.higherKinds

object UseCaseCont {
  def apply[F[_], A](f: (A => F[UseCaseResult]) => F[UseCaseResult]): UseCaseCont[F, A] =
    ContT(f)

  def point[F[_], A](a: => A): UseCaseCont[F, A] = ContT(f => f(a))

  //def liftM[M[_]: Monad, A](ma: M[A])(implicit mi: Monad[A]): UseCaseCont[M, A] = ContT(f => Monad[M].point(ma)(f))

  def fromIO[A](io: IO[A]): UseCaseCont[IO, A] = ContT(io.flatMap)

  def fromFuture[A](future: Future[A])(implicit ec: ExecutionContext): UseCaseCont[Future, A] =
    ContT(future.flatMap(_))

  def successful[A](a: A)(implicit ec: ExecutionContext): UseCaseCont[Future, A] =
    fromFuture(Future.successful(a))

  def failed[A](throwable: Throwable)(implicit ec: ExecutionContext): UseCaseCont[Future, A] =
    fromFuture(Future.failed(throwable))
}
