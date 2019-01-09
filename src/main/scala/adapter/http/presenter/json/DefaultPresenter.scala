package adapter.http.presenter.json

import application.usecase._
import io.circe.syntax._
import play.api.mvc.Result
import play.api.mvc.Results.{BadRequest, Conflict, InternalServerError, NotFound, Ok}

final class DefaultPresenter
  extends Presenter[UseCaseResult, Result]
    with JsonEncoder
    with WritableJsonConverter {

  def present(arg: UseCaseResult): Result = {
    arg match {
      case NormalCase => Ok
      case res: EntityDuplicated => Conflict(res.asJson)
      case res: EntityNotFound => NotFound(res.asJson)
      case res: InvalidInputParameters => BadRequest(res.asJson(formErrorEncoder))
      case res: AbnormalCase => InternalServerError(res.asJson)
    }
  }

}
