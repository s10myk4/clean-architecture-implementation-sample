package application

import application.usecase.UseCaseResult
import scalaz.ContT
import scala.language.higherKinds

package object cont {
  type ActionCont[F[_], A] = ContT[F, UseCaseResult, A]
}
