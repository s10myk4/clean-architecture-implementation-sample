package com.s10myk4.adapter.http

import com.s10myk4.adapter.http.controller.WarriorController
import com.s10myk4.adapter.http.presenter.json.DefaultPresenter
import com.s10myk4.application.usecase.UseCaseComponent
import play.api.mvc.ControllerComponents

import scala.concurrent.ExecutionContext

trait HttpComponent {
  _: UseCaseComponent =>

  def controllerComponents: ControllerComponents

  lazy val ec: ExecutionContext = ExecutionContext.global

  lazy val defaultPresenter: DefaultPresenter = new DefaultPresenter

  lazy val warriorController: WarriorController =
    new WarriorController(controllerComponents, findWarrior, equipNewWeaponToWarrior, defaultPresenter, ec)

}
