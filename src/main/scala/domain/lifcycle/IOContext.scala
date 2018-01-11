package domain.lifcycle

import scala.concurrent.ExecutionContext

trait IOContext {
  val ec: ExecutionContext
}
