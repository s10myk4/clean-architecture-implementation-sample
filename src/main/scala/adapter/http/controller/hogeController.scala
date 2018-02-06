package adapter.http.controller

import adapter.http.form.EquipNewWeaponForm
import domain.lifcycle.WarriorRepository
import domain.model.character.warrior.{Warrior, WarriorId}
import play.api.mvc.{AbstractController, ControllerComponents, EssentialAction}

import scala.concurrent.{ExecutionContext, Future}
import scalaz.std.scalaFuture._

class hogeController(
  cc: ControllerComponents,
  warriorRepository: WarriorRepository[Future],
) extends AbstractController(cc) {

  private val ec = ExecutionContext.global

  implicit val fm = futureInstance(ec)

  def hoge: EssentialAction = Action.async { implicit r =>
    EquipNewWeaponForm.apply.bindFromRequest().fold(
      err => Future.successful(BadRequest(err.errors.map(_.message).mkString(","))),
      form => {
        warriorRepository.resolveBy(WarriorId(form.warriorId)).flatMap {
          case Some(w) =>
            w.setNewWeapon(form.weapon) match {
              case Right(w: Warrior) => warriorRepository.store(w).map(_ => Ok)(ec)
              case Left(e) => Future.successful(BadRequest(e.getMessage))
            }
          case None => Future.successful(BadRequest)
        }(ec)
      }
    )
  }

}
