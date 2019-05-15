package com.s10myk4.application.usecase

import com.s10myk4.application.cont.UseCaseCont
import com.s10myk4.application.support.UseCaseCont
import com.s10myk4.domain.lifcycle.WarriorRepository
import com.s10myk4.domain.model.character.warrior.Warrior.{DifferentAttributeError, NotOverLevelError}
import com.s10myk4.domain.model.character.warrior.{Warrior, WarriorError}
import com.s10myk4.domain.model.weapon.Weapon
import cats.Monad
import cats.data.NonEmptyList
import cats.data.Validated.{Invalid, Valid}
import cats.syntax.flatMap._

import scala.language.higherKinds

/**
  * 戦士に新しい武器を装備する
  */
//UseCaseの異常系とドメインの異常を型としてどう扱うか
//基本的には意識しない方向にしたい
final class EquipNewWeaponToWarrior[F[_]: Monad](
    repository: WarriorRepository[F]
) {

  import EquipNewWeaponToWarrior._

  def apply(warrior: Warrior, newWeapon: Weapon): UseCaseCont[F, UseCaseResult] =
    UseCaseCont { f =>
      warrior.equip(newWeapon) match {
        case Valid(w)     => repository.store(w).flatMap(_ => f(NormalCase))
        case Invalid(err) => Monad[F].point(err)
      }
    }
}

object EquipNewWeaponToWarrior {

  final case class EquipNewWeaponToWarriorInput(
      warriorId: Long,
      weapon: Weapon
  )

  implicit def toUseCaseResult(domainErrors: NonEmptyList[WarriorError]): UseCaseResult =
    domainErrors match {
      case NonEmptyList(h: DifferentAttributeError, t) if t.isEmpty =>
        DeffrentAttribute()
      case NonEmptyList(h: NotOverLevelError, t) if t.isEmpty => NotOverLevel()
      case _                                                  => DeffrentAttributeAndNotOverLevel()
    }

  final case class DeffrentAttributeAndNotOverLevel() extends AbnormalCase {
    override val cause: String = ""
  }

  final case class DeffrentAttribute() extends AbnormalCase {
    val cause: String = "A is deffernt warrior attribute"
  }

  final case class NotOverLevel() extends AbnormalCase {
    val cause: String = ""
  }

  /*
  case object InvalidCondition extends AbnormalCase {
    val cause: String = "この武器を装備するための条件を満たしていません"
  }
 */
}
