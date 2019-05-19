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
final class EquipWeaponToWarrior[F[_]: Monad](
    repository: WarriorRepository[F]
) {

  import EquipWeaponToWarrior._

  def exec(warrior: Warrior, newWeapon: Weapon): UseCaseCont[F, UseCaseResult] =
    UseCaseCont { f =>
      warrior.equip(newWeapon) match {
        case Valid(w)     => repository.store(w).flatMap(_ => f(NormalCase))
        case Invalid(err) => Monad[F].point(err)
      }
    }
}

object EquipWeaponToWarrior {

  final case class EquipWeaponToWarriorInput(
      weapon: Weapon
  )

  implicit def toUseCaseResult(domainErrors: NonEmptyList[WarriorError]): UseCaseResult =
    domainErrors match {
      case NonEmptyList(h: DifferentAttributeError, t) if t.isEmpty => DifferentAttribute(h)
      case NonEmptyList(h: NotOverLevelError, t) if t.isEmpty       => NotOverLevel(h)
      //case _                                                        => DifferentAttributeAndNotOverLevel()
    }

  final case class DifferentAttributeAndNotOverLevel(err1: DifferentAttributeError, err2: NotOverLevelError)
      extends AbnormalCase {
    val cause: String = s"${DifferentAttribute(err1).cause} and ${NotOverLevel(err2).cause}"
  }

  final case class DifferentAttribute(err: DifferentAttributeError) extends AbnormalCase {
    val cause: String =
      s"Weapon attribute:${err.weapon.attribute.entryName} is different warrior attribute:${err.warriorAttr.entryName}"
  }

  final case class NotOverLevel(err: NotOverLevelError) extends AbnormalCase {
    val cause: String =
      s"Warrior level:${err.warriorLevel.value} is not over weapon level:${err.weapon.levelConditionOfEquipment}"
  }

}
