package adapter.http.controller.support

import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

private[controller] abstract class BaseController(cc: ControllerComponents)
    extends AbstractController(cc)
    with HttpStatusConverter
    with FormHelper {

  protected val ec: ExecutionContext = ExecutionContext.global

}
