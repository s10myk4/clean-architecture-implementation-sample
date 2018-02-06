package adapter.http.controller

import adapter.http.controller.support.{FormHelper, HttpStatusConverter}
import adapter.http.form.EquipNewWeaponForm
import application.usecase.{IdentifyWarrior, WarriorEquippedNewWeapon}
import domain.model.character.warrior.WarriorId
import play.api.mvc._

import scala.concurrent.ExecutionContext
import scalaz.std.scalaFuture

class WarriorController(
  cc: ControllerComponents,
  identifyWarrior: IdentifyWarrior,
  warriorEquippedNewWeapon: WarriorEquippedNewWeapon,
) extends AbstractController(cc) with HttpStatusConverter with FormHelper {

  private val ec: ExecutionContext = ExecutionContext.global

  def equipNewWeapon: EssentialAction = Action.async { r =>
    implicit val fm = scalaFuture.futureInstance(ec)
    (for {
      form <- bindCont(EquipNewWeaponForm.apply)(r)
      (warriorId, weapon) = (WarriorId(form.warriorId), form.weapon)
      warrior <- identifyWarrior.conduct(warriorId)
      res <- warriorEquippedNewWeapon.conduct(warrior, weapon)
    } yield res)
      .run_.map(_.convertHttpStatus)(ec)
  }

}

