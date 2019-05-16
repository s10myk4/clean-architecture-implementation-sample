package com.s10myk4.application.usecase

import cats.Monad
import cats.data.NonEmptyList
import cats.data.Validated.{Invalid, Valid}
import cats.syntax.flatMap._
import com.s10myk4.application.cont.UseCaseCont
import com.s10myk4.application.support.UseCaseCont
import com.s10myk4.domain.lifcycle.WarriorRepository
import com.s10myk4.domain.model.character.warrior.Warrior.{DifferentAttributeError, NotOverLevelError}
import com.s10myk4.domain.model.character.warrior.{Warrior, WarriorError}
import com.s10myk4.domain.model.weapon.Weapon

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
      weapon: Weapon
  )

  implicit def toUseCaseResult(domainErrors: NonEmptyList[WarriorError]): UseCaseResult =
    domainErrors match {
      case NonEmptyList(h: DifferentAttributeError, t) if t.isEmpty => DifferentAttribute(h)
      case NonEmptyList(h: NotOverLevelError, t) if t.isEmpty       => NotOverLevel(h)
      case _                                                        => DeffrentAttributeAndNotOverLevel()
    }

  final case class DeffrentAttributeAndNotOverLevel() extends AbnormalCase {
    override val cause: String = ""
  }

  final case class DifferentAttribute(err: DifferentAttributeError) extends AbnormalCase {
    val cause: String =
      s"The ${err.weapon.attribute.entryName} is different warrior attribute of ${err.warriorAttr.entryName}"
  }

  final case class NotOverLevel(err: NotOverLevelError) extends AbnormalCase {
    val cause: String = ""
  }

}
