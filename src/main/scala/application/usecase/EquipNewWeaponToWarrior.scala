package application.usecase

import application.cont.ActionCont
import application.support.ActionCont
import application.usecase.EquipNewWeaponToWarrior.InvalidCondition
import domain.lifcycle.WarriorRepository
import domain.model.character.warrior.Warrior
import domain.model.weapon.Weapon
import scalaz.Monad
import scalaz.syntax.monad._

import scala.language.higherKinds

/**
  * 戦士に新しい武器を装備する
  */
final class EquipNewWeaponToWarrior[F[_] : Monad](
  repository: WarriorRepository[F]
) {

  def apply(warrior: Warrior, newWeapon: Weapon): ActionCont[F, UseCaseResult] =
    ActionCont { f =>
      warrior.equip(newWeapon) match {
        case Right(w) => repository.store(w).flatMap(_ => f(NormalCase))
        case Left(_) => Monad[F].point(InvalidCondition)
      }
    }
}

object EquipNewWeaponToWarrior {

  final case class EquipNewWeaponToWarriorInput(
    warriorId: Long,
    weapon: Weapon
  )

  case object InvalidCondition extends AbnormalCase {
    val cause: String = "この武器を装備するための条件を満たしていません"
  }

}
