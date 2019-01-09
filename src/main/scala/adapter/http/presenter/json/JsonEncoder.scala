package adapter.http.presenter.json

import application.usecase.{AbnormalCase, InvalidInputParameters}
import io.circe.{Encoder, Json}

/**
  * HttpレスポンスをJsonに変換するエンコーダー
  */
private[presenter] trait JsonEncoder {

  implicit def failureResponseEncoder[T <: AbnormalCase]: Encoder[T] = {
    Encoder.instance { f =>
      Json.obj("message" -> Json.fromString(f.cause))
    }
  }

  implicit def formErrorEncoder: Encoder[InvalidInputParameters] = {
    Encoder.instance { error =>
      val message = "message" -> Json.fromString(error.cause)
      val formErrors = error.errors.map {
        case (key, value) => key -> Json.fromString(value)
      }.toSeq
      Json.obj(message +: formErrors: _*)
    }
  }
}
