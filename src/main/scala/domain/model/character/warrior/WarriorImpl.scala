package domain.model.character.warrior

import domain.model.weapon.Weapon
import domain.model.{Attribute, Level}

import scalaz.{-\/, \/, \/-}

private[domain] final case class WarriorImpl(
  id: WarriorId,
  name: String,
  attribute: Attribute,
  weapon: Option[Weapon],
  level: Level,
) extends Warrior {

  weapon.foreach(w => require(isValidEquipmentCondition(w)))

  def setNewWeapon(weapon: Weapon): IllegalArgumentException \/ Warrior = {
    -\/(require(isValidEquipmentCondition(weapon)))
    \/-(this.copy(weapon = Some(weapon)))
  }

  private def isValidEquipmentCondition(weapon: Weapon): Boolean = {
    //属性が一緒か & 条件となるlevelを満たしているか
    attribute == weapon.attribute & weapon.levelConditionOfEquipment <= level.value
  }

}
