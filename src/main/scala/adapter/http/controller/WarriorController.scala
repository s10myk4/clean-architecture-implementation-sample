package adapter.http.controller

import adapter.http.controller.support.FormHelper
import adapter.http.form.EquipNewWeaponForm
import application.usecase.{EquipNewWeaponToWarrior, FindWarrior, Presenter, UseCaseResult}
import domain.model.character.warrior.WarriorId
import play.api.mvc._
import scalaz.Monad
import scalaz.std.scalaFuture.futureInstance

import scala.concurrent.{ExecutionContext, Future}

class WarriorController(
  cc: ControllerComponents,
  findWarrior: FindWarrior[Future],
  warriorEquippedNewWeapon: EquipNewWeaponToWarrior[Future],
  presenter: Presenter[UseCaseResult, Result],
  ec: ExecutionContext
) extends AbstractController(cc) with FormHelper {

  implicit val futureMonad: Monad[Future] = futureInstance(ec)

  def equipNewWeapon: EssentialAction = Action.async { implicit r =>
    //継続モナドを合成
    val composedCont = for {
      form <- EquipNewWeaponForm.apply.bindCont
      (warriorId, weapon) = (WarriorId(form.warriorId), form.weapon)
      warrior <- findWarrior.apply(warriorId)
      res <- warriorEquippedNewWeapon.apply(warrior, weapon)
    } yield res
    //合成した継続モナドに Futureのモナドインスタンスを適用して実行
    val res = composedCont.run_
    //ユースケースの結果をplay.api.mvc.Resultに変換
    res.map(presenter.present)(ec)
  }

}
