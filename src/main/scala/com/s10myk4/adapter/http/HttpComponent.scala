package com.s10myk4.adapter.http

import com.s10myk4.adapter.http.controller.WarriorController
import com.s10myk4.adapter.http.presenter.json.WarriorPresenter
import com.s10myk4.application.usecase.{Presenter, UseCaseComponent, UseCaseResult}
import play.api.mvc.{ControllerComponents, Result}

import scala.concurrent.ExecutionContext

trait HttpComponent { _: UseCaseComponent =>

  def controllerComponents: ControllerComponents

  lazy val ec: ExecutionContext = ExecutionContext.global

  lazy val presenter: Presenter[UseCaseResult, Result] = new WarriorPresenter

  lazy val warriorController: WarriorController =
    new WarriorController(controllerComponents, findWarrior, equipNewWeaponToWarrior, presenter, ec)

}
