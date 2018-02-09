package adapter.http.controller

import adapter.http.controller.support.{FormHelper, HttpStatusConverter}
import adapter.http.form.EquipNewWeaponForm
import application.usecase.{FindWarrior, WarriorEquippedNewWeapon}
import domain.model.character.warrior.WarriorId
import play.api.mvc._

import scala.concurrent.ExecutionContext
import scalaz.std.scalaFuture

class WarriorController(
  cc: ControllerComponents,
  findWarrior: FindWarrior,
  warriorEquippedNewWeapon: WarriorEquippedNewWeapon,
) extends AbstractController(cc) with HttpStatusConverter with FormHelper {

  private val ec: ExecutionContext = ExecutionContext.global

  def equipNewWeapon: EssentialAction = Action.async { r =>
    implicit val fm = scalaFuture.futureInstance(ec)

    //継続モナドを合成
    val composedConts = for {
      form <- bindCont(EquipNewWeaponForm.apply)(r)
      (warriorId, weapon) = (WarriorId(form.warriorId), form.weapon)
      warrior <- findWarrior(warriorId)
      res <- warriorEquippedNewWeapon(warrior, weapon)
    } yield res

    //合成した継続モナドに Futureのモナドインスタンスを適用して実行
    val res = composedConts.run_
    //ユースケースの実行結果をplay.api.mvc.Resultに変換
    res.map(_.convertHttpStatus)(ec)
  }

}
