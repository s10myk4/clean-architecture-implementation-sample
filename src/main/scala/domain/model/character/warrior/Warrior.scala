package domain.model.character.warrior

import domain.model.character.Character
import domain.model.weapon.Weapon
import domain.model.{Attribute, Level}

import scalaz.\/

trait Warrior extends Character[WarriorId] {
  val name: String
  val attribute: Attribute
  val weapon: Option[Weapon]
  val level: Level

  def setNewWeapon(weapon: Weapon): IllegalArgumentException \/ Warrior
}

object Warrior {
  def apply(
    id: WarriorId,
    name: String,
    weapon: Option[Weapon],
    attribute: Attribute,
    level: Level,
  ): Warrior = WarriorImpl(id, name, attribute, weapon, level)
}
