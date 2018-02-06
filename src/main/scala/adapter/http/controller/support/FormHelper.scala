package adapter.http.controller.support

import application.cont.ActionCont
import application.usecase.{InvalidInputParameters, UseCaseResult}
import play.api.data.{Form, FormError}
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.Future
import scalaz.ContT

private[http] trait FormHelper {

  def bindCont[A](form: Form[A])(implicit req: Request[AnyContent]): ActionCont[A] =
    ContT(f =>
      form.bindFromRequest.fold[Future[UseCaseResult]](
        error => Future.successful(InvalidInputParameters("不正な内容です", convertFormErrorsToMap(error.errors))),
        a => f(a)
      )
    )

  def convertFormErrorsToMap(errors: Seq[FormError]): Map[String, String] = {
    errors.map(e => e.key -> e.message).toMap
  }
}
