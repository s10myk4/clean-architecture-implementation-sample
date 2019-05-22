package com.s10myk4.adapter.http.controller

import cats.Applicative
import cats.data.ContT
import cats.effect.IO
import com.s10myk4.adapter.http.controller.support.{ActionSupport, FormHelper}
import com.s10myk4.adapter.http.form.EquipWeaponForm
import com.s10myk4.application.usecase.{EquipWeaponToWarrior, FindWarrior, Presenter, UseCaseResult}
import com.s10myk4.domain.model.character.warrior.WarriorId
import play.api.mvc._

import scala.concurrent.ExecutionContext

final class WarriorController(
    cc: ControllerComponents,
    findWarrior: FindWarrior[IO],
    warriorEquippedNewWeapon: EquipWeaponToWarrior[IO],
    presenter: Presenter[UseCaseResult, Result],
    val ec: ExecutionContext
) extends AbstractController(cc)
    with FormHelper
    with ActionSupport {

  def equipWeapon(id: Long): EssentialAction = Action.async { implicit r =>
    //継続モナドを合成
    val composedCont: ContT[IO, UseCaseResult, UseCaseResult] = for {
      form <- EquipWeaponForm.apply.bindCont[IO]
      (warriorId, weapon) = (WarriorId(id), form.weapon)
      warrior <- findWarrior.exec(warriorId)
      res     <- warriorEquippedNewWeapon.exec(warrior, weapon)
    } yield res

    //合成した継続モナドに IOのApplicative Functorを適用し実行する
    val res = composedCont.run(Applicative[IO].pure)
    //ユースケースの結果をplay.api.mvc.Resultに変換
    res.map(presenter.present).toFuture
  }

}
