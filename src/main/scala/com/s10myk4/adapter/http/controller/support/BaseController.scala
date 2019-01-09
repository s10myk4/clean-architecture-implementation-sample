package com.s10myk4.adapter.http.controller.support

import play.api.mvc.{AbstractController, ControllerComponents}

private[controller] abstract class BaseController(cc: ControllerComponents)
  extends AbstractController(cc)
    with FormHelper
