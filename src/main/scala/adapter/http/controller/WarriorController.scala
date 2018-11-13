package adapter.http.controller

import adapter.http.controller.support.{FormHelper, HttpStatusConverter}
import adapter.http.form.EquipNewWeaponForm
import application.IoCtx.IoCtx
import application.usecase.{FindWarrior, WarriorEquippedNewWeapon}
import domain.model.character.warrior.WarriorId
import play.api.mvc._
import scalaz.Monad
import scalaz.std.scalaFuture.futureInstance

import scala.concurrent.{ExecutionContext, Future}

class WarriorController(
  cc: ControllerComponents,
  findWarrior: FindWarrior[IoCtx],
  warriorEquippedNewWeapon: WarriorEquippedNewWeapon[IoCtx],
) extends AbstractController(cc) with HttpStatusConverter with FormHelper {

  private val ec: ExecutionContext = ExecutionContext.global //TODO
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
    //ユースケースの実行結果をplay.api.mvc.Resultに変換
    res.map(_.convertHttpStatus)(ec)
  }

}
