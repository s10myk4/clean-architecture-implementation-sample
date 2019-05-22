package com.s10myk4.adapter.http.presenter.json

import com.s10myk4.application.usecase.EquipWeaponToWarrior.{
  DifferentAttribute,
  DifferentAttributeAndNotOverLevel,
  NotOverLevel
}
import com.s10myk4.application.usecase.{AbnormalCase, InvalidInputParameters, NormalCase, UseCaseResult}
import io.circe.syntax._
import play.api.mvc.Result
import play.api.mvc.Results._

private[http] final class WarriorPresenter extends JsonPresenter {
  def present(arg: UseCaseResult): Result =
    arg match {
      case NormalCase                                => Ok
      case e: DifferentAttribute.type                => BadRequest(e.asJson)
      case e: NotOverLevel.type                      => BadRequest(e.asJson)
      case e: DifferentAttributeAndNotOverLevel.type => BadRequest(e.asJson)
      case e: InvalidInputParameters                 => BadRequest(e.asJson(formErrorEncoder))
      case e: AbnormalCase                           => InternalServerError(e.asJson)
    }
}
