package adapter.http

import adapter.http.controller.WarriorController
import adapter.http.presenter.json.DefaultPresenter
import application.usecase.UseCaseComponent
import play.api.mvc.ControllerComponents
import scalaz.Monad
import scalaz.std.scalaFuture._

import scala.concurrent.{ExecutionContext, Future}

trait HttpComponent {
  _: UseCaseComponent =>

  def controllerComponents: ControllerComponents

  lazy val ec: ExecutionContext = ExecutionContext.global

  lazy val futureMonad: Monad[Future] = futureInstance(ec)

  lazy val defaultPresenter: DefaultPresenter = new DefaultPresenter

  lazy val warriorController: WarriorController =
    new WarriorController(controllerComponents, findWarrior, equipNewWeaponToWarrior, defaultPresenter, ec)

}
