package application.usecase

import application.cont.ActionCont
import application.support.cont.ActionCont
import domain.lifcycle.{IOContext, WarriorRepository}
import domain.model.character.warrior.Warrior
import domain.model.weapon.Weapon

import scala.concurrent.Future
import scala.language.higherKinds

/**
  * 戦士に新しい武器を装備する
  */
final class WarriorEquippedNewWeaponImpl[F[_]](
  repository: WarriorRepository[Future]
)
  extends WarriorEquippedNewWeapon {
  def apply(warrior: Warrior, newWeapon: Weapon): ActionCont[UseCaseResult] =
    ActionCont { f =>
      warrior.equip(newWeapon) match {
        case Right(w) => repository.store(w).flatMap(_ => f(NormalCase))(ctx.ec)
        case Left(_) => Future.successful(InvalidCondition)
      }
    }
}

object WarriorEquippedNewWeapon {

  final case class EquipNewWeaponInput(
    warriorId: Long,
    weapon: Weapon
  )

  case object InvalidCondition extends AbnormalCase {
    val cause: String = "この武器を装備するための条件を満たしていません"
  }
}
