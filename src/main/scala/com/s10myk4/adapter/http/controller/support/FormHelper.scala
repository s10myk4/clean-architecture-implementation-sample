package com.s10myk4.adapter.http.controller.support

import com.s10myk4.application.cont.ActionCont
import com.s10myk4.application.usecase.{InvalidInputParameters, UseCaseResult}
import play.api.data.{Form, FormError}
import play.api.mvc.{AnyContent, Request}
import scalaz.{Applicative, ContT}

import scala.language.higherKinds

private[http] trait FormHelper {

  implicit class FormOps[A](form: Form[A]) {
    def bindCont[F[_] : Applicative](implicit req: Request[AnyContent]): ActionCont[F, A] =
      ContT(f =>
        form.bindFromRequest.fold[F[UseCaseResult]](
          error => Applicative[F].pure(InvalidInputParameters("不正な内容です", convertFormErrorsToMap(error.errors))),
          a => f(a)
        )
      )
  }

  private def convertFormErrorsToMap(errors: Seq[FormError]): Map[String, String] = {
    errors.map(e => e.key -> e.message).toMap
  }
}
