package com.s10myk4.domain.model.character.warrior

import cats.data.ValidatedNel
import com.s10myk4.domain.model.character.warrior.Warrior.{DifferentAttributeError, NotOverLevelError}
import com.s10myk4.domain.model.weapon.Weapon
import com.s10myk4.domain.model.{Attribute, BaseEntity}

import cats.syntax.validated._


sealed abstract case class Warrior(
                                    id: WarriorId,
                                    name: WarriorName,
                                    attribute: Attribute,
                                    weapon: Option[Weapon],
                                    level: WarriorLevel,
                                  ) extends BaseEntity[WarriorId] {

  def equip(weapon: Weapon): ValidatedNel[WarriorError, Warrior] = {
    if (!isSameAttribute(weapon)) new DifferentAttributeError().invalidNel
    else if (!isOverLevel(weapon)) new NotOverLevelError().invalidNel
    else new Warrior(id, name, attribute, Some(weapon), level) {}.validNel
  }

  private def isSameAttribute(weapon: Weapon): Boolean = attribute == weapon.attribute

  private def isOverLevel(weapon: Weapon): Boolean = level.value >= weapon.levelConditionOfEquipment

}

object Warrior {
  def createWithoutWeapon(
                           id: WarriorId,
                           name: WarriorName,
                           attribute: Attribute,
                           level: WarriorLevel
                         ): Warrior = {
    new Warrior(id, name, attribute, None, level) {}
  }

  /*
  def createDefault(id: WarriorId): ValidationNel[WarriorError, Warrior] = {
    (WarriorName("default" + id.value) |@| WarriorLevel(1)) {
      (name, level) =>
        new Warrior(id, name, Attribute.NormalAttribute, None, level){}
    }
  }
  */

  def createDefault(id: WarriorId): ValidatedNel[WarriorError, Warrior] = ???

  final class DifferentAttributeError extends WarriorError

  final class NotOverLevelError extends WarriorError

}