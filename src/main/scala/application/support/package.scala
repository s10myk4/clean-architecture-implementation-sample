package application

import application.usecase.UseCaseResult

import scala.concurrent.Future
import scalaz.ContT

package object cont {
  type ActionCont[A] = ContT[Future, UseCaseResult, A]
}
