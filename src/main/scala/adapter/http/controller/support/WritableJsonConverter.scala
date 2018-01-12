package adapter.http.controller.support

import io.circe.{Json, Printer}
import play.api.http.{ContentTypeOf, ContentTypes, Writeable}
import play.api.mvc.Codec

/**
  * CirceJsonをPlayが書き込みできる形式にするためのコンバーター
  */
trait WritableJsonConverter {
  implicit def writableOfCirceJson(implicit codec: Codec, printer: Printer = Printer.noSpaces): Writeable[Json] = {
    Writeable(obj => codec.encode(obj.pretty(printer)))
  }

  implicit def contentTypeOfCirceJson: ContentTypeOf[Json] = {
    ContentTypeOf[Json](Some(ContentTypes.JSON))
  }
}
