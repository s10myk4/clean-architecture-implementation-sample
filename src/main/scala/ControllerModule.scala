import adapter.http.controller.WarriorController
import application.usecase.{FindWarrior, WarriorEquippedNewWeapon}
import com.softwaremill.macwire.wire
import play.api.mvc.ControllerComponents

trait ControllerModule {
  self: ServiceModule =>

  def controllerComponents: ControllerComponents

  //def findWarrior: FindWarrior

  //def warriorEquippedNewWeapon: WarriorEquippedNewWeapon

  lazy val warriorController: WarriorController = wire[WarriorController]

}
