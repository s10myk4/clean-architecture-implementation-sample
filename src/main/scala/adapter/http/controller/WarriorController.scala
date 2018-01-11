package adapter.http.controller

import adapter.http.controller.support.BaseController
import adapter.http.form.EquipNewWeaponForm
import application.usecase.{IdentifyWarrior, RegisterWarrior, WarriorEquippedNewWeapon}
import domain.model.character.warrior.WarriorId
import play.api.mvc._

import scalaz.std.scalaFuture

class WarriorController(
  cc: ControllerComponents,
  identifyWarrior: IdentifyWarrior,
  warriorEquippedNewWeapon: WarriorEquippedNewWeapon,
  registerWarrior: RegisterWarrior
) extends BaseController(cc) {

  def equipNewWeapon: EssentialAction = Action.async { r =>
    implicit val fm = scalaFuture.futureInstance(ec)
    (for {
      form <- bindCont(EquipNewWeaponForm.apply)(r)
      (warriorId, weapon) = (WarriorId(form.warriorId), form.weapon)
      warrior <- identifyWarrior.conduct(warriorId)
      newWarrior <- warriorEquippedNewWeapon.conduct(warrior, weapon)
      res <- registerWarrior.conduct(newWarrior)
    } yield res).run_.map(_.convertHttpStatus)(ec)
  }

}

