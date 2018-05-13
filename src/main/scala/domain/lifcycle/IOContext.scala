package domain.lifcycle

import scala.concurrent.ExecutionContext

trait IOContext {
  val ec: ExecutionContext
}

object DefaultIOContext extends IOContext {
  val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global
}
