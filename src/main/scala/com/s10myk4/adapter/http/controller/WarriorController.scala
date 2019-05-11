package com.s10myk4.adapter.http.controller

import cats.Applicative
import cats.data.ContT
import cats.effect.IO
import com.s10myk4.adapter.http.controller.support.FormHelper
import com.s10myk4.adapter.http.form.EquipNewWeaponForm
import com.s10myk4.application.usecase.{EquipNewWeaponToWarrior, FindWarrior, Presenter, UseCaseResult}
import com.s10myk4.domain.model.character.warrior.WarriorId
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class WarriorController(
  cc: ControllerComponents,
  findWarrior: FindWarrior[IO],
  warriorEquippedNewWeapon: EquipNewWeaponToWarrior[IO],
  presenter: Presenter[UseCaseResult, Result],
  ec: ExecutionContext
) extends AbstractController(cc) with FormHelper {

  def equipNewWeapon: EssentialAction = Action.async { implicit r =>
    //継続モナドを合成
    val composedCont: ContT[IO, UseCaseResult, UseCaseResult] = for {
      form <- EquipNewWeaponForm.apply.bindCont[IO]
      (warriorId, weapon) = (WarriorId(form.warriorId), form.weapon)
      warrior <- findWarrior.apply(warriorId)
      res <- warriorEquippedNewWeapon.apply(warrior, weapon)
    } yield res

    //合成した継続モナドに Futureのモナドインスタンスを適用して実行
    val res = Future(composedCont.run(Applicative[IO].pure).unsafeRunSync())(ec)
    //ユースケースの結果をplay.api.mvc.Resultに変換
    res.map(presenter.present)(ec)
  }

}
