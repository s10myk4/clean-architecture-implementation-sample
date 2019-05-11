package com.s10myk4.application

import com.s10myk4.application.usecase.UseCaseResult
import scala.language.higherKinds
import cats.data.ContT

package object cont {
  type ActionCont[F[_], A] = ContT[F, UseCaseResult, A]
}
