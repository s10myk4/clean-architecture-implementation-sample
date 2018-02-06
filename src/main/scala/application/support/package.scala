package application

import application.usecase.UseCaseResult

import scala.concurrent.Future
import scalaz.ContT

package object cont {
  type ActionCont[A] = ContT[Future, UseCaseResult, A]
}

/*
final case class Cont[B, A](run: (A => B) => B) {
  def map[C](f: B => C): Cont[B, C] = Cont(g => run(a => g(f(a))))
  def flatMap[C](f: A => Cont[B, C]): Cont[B, C] = Cont(g => run(a => f(a).run(g)))
}
*/


