package application.usecase

import application.cont.ActionCont
import application.support.ActionCont
import domain.lifcycle.{IOContext, WarriorRepository}
import domain.model.character.warrior.Warrior
import domain.model.weapon.Weapon

import scala.concurrent.Future
import scalaz.{-\/, \/-}

trait WarriorEquippedNewWeapon {
  def conduct(warrior: Warrior, newWeapon: Weapon): ActionCont[UseCaseResult, UseCaseResult]

  case object InvalidCondition extends Abnormality {
    val cause: String = "この武器を装備するための条件を満たしていません"
  }

}

final class WarriorEquippedNewWeaponImpl[Ctx <: IOContext](
  ctx: Ctx,
  repository: WarriorRepository[Future]
) extends WarriorEquippedNewWeapon {
  def conduct(warrior: Warrior, newWeapon: Weapon): ActionCont[UseCaseResult, UseCaseResult] =
    ActionCont { f =>
      warrior.setNewWeapon(newWeapon) match {
        case \/-(w) => repository.store(w).flatMap(_ => f(Normality))(ctx.ec)
        case -\/(_) => Future.successful(InvalidCondition)
      }
    }
}
