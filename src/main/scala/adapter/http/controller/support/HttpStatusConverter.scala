package adapter.http.controller.support

import application.usecase._
import io.circe.syntax._
import play.api.mvc.Result
import play.api.mvc.Results._

/**
  * ユースケースの実行結果をhttpStatusに変換する機能
  */
private[http] trait HttpStatusConverter extends JsonEncoder with WritableJsonConverter {

  implicit class StatusConverter(result: UseCaseResult) {
    def convertHttpStatus: Result = {
      result match {
        case NormalCase => Ok
        case x: EntityDuplicated => Conflict(x.asJson)
        case x: EntityNotFound => NotFound(x.asJson)
        case x: InvalidInputParameters => BadRequest(x.asJson(formErrorEncoder))
      }
    }
  }

}
