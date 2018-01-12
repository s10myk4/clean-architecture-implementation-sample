package adapter.http.controller

import adapter.http.controller.support.BaseController
import adapter.http.form.EquipNewWeaponForm
import application.usecase.{IdentifyWarrior, WarriorEquippedNewWeapon}
import domain.model.character.warrior.WarriorId
import play.api.mvc._

import scalaz.std.scalaFuture

class WarriorController(
  cc: ControllerComponents,
  identifyWarrior: IdentifyWarrior,
  warriorEquippedNewWeapon: WarriorEquippedNewWeapon,
) extends BaseController(cc) {

  def equipNewWeapon: EssentialAction = Action.async { r =>
    implicit val fm = scalaFuture.futureInstance(ec)
    (for {
      form <- bindCont(EquipNewWeaponForm.apply)(r)
      (warriorId, weapon) = (WarriorId(form.warriorId), form.weapon)
      warrior <- identifyWarrior.conduct(warriorId)
      res <- warriorEquippedNewWeapon.conduct(warrior, weapon)
    } yield res).run_.map(_.convertHttpStatus)(ec)
  }

}

