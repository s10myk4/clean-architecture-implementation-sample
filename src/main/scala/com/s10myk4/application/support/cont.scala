package com.s10myk4.application

import com.s10myk4.application.usecase.UseCaseResult
import scalaz.ContT
import scala.language.higherKinds

package object cont {
  type ActionCont[F[_], A] = ContT[F, UseCaseResult, A]
}
