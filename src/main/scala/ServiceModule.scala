import application.usecase.{FindWarrior, FindWarriorImpl, WarriorEquippedNewWeapon, WarriorEquippedNewWeaponImpl}
import com.softwaremill.macwire.wire
import domain.lifcycle.{IOContext, WarriorRepository}

import scala.concurrent.{ExecutionContext, Future}

trait ServiceModule {
  self: RepositoryModule =>

  //def warriorRepository: WarriorRepository[Future]

  lazy val ctx: IOContext = new IOContext {
    val ec = ExecutionContext.global
    //AutoSession
  }

  lazy val findWarrior: FindWarrior = wire[FindWarriorImpl[IOContext]]

  lazy val warriorEquippedNewWeapon: WarriorEquippedNewWeapon =
    wire[WarriorEquippedNewWeaponImpl[IOContext]]

}
