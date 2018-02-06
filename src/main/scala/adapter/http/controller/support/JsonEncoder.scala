package adapter.http.controller.support

import application.usecase.{Abnormality, InvalidInputParameters}
import io.circe.{Encoder, Json}

/**
  * HttpレスポンスをJsonに変換するエンコーダー
  */
private[controller] trait JsonEncoder {

  implicit def failureResponseEncoder[T <: Abnormality]: Encoder[T] = {
    Encoder.instance { f =>
      Json.obj("message" -> Json.fromString(f.cause))
    }
  }

  implicit def formErrorEncoder: Encoder[InvalidInputParameters] = {
    Encoder.instance { error =>
      val message = "message" -> Json.fromString(error.cause)
      val formErrors = error.errors.map {
        case (k, m) =>
          k -> Json.fromString(m)
      }.toSeq
      Json.obj(message +: formErrors: _*)
    }
  }
}
