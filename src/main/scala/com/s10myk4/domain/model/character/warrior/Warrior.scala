package com.s10myk4.domain.model.character.warrior

import cats.data.ValidatedNel
import cats.implicits._
import com.s10myk4.domain.model.weapon.Weapon
import com.s10myk4.domain.model.{Attribute, BaseEntity}

sealed abstract case class Warrior(
    id: WarriorId,
    name: WarriorName,
    attribute: Attribute,
    weapon: Option[Weapon],
    level: WarriorLevel
) extends BaseEntity[WarriorId] {

  import Warrior._

  def equip(weapon: Weapon): ValidatedNel[WarriorError, Warrior] = {
    (isNotSameAttribute(weapon), isNotOverLevel(weapon))
      .mapN((_, _) => new Warrior(id, name, attribute, Some(weapon), level) {})
  }

  private def isNotSameAttribute(weapon: Weapon): ValidatedNel[WarriorError, Weapon] =
    if (attribute == weapon.attribute) weapon.validNel else DifferentAttributeError.invalidNel

  private def isNotOverLevel(weapon: Weapon): ValidatedNel[WarriorError, Weapon] =
    if (level.value >= weapon.levelConditionOfEquipment) weapon.validNel else NotOverLevelError.invalidNel

}

object Warrior {
  def createWithoutWeapon(
      id: WarriorId,
      name: WarriorName,
      attribute: Attribute,
      level: WarriorLevel
  ): Warrior = new Warrior(id, name, attribute, None, level) {}

  object DifferentAttributeError extends WarriorError

  object NotOverLevelError extends WarriorError
}
