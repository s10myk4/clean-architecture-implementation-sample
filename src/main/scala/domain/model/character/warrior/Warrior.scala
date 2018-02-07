package domain.model.character.warrior

import domain.model.exception.ConditionViolatedException
import domain.model.weapon.Weapon
import domain.model.{Attribute, BaseEntity, Level}

/**
  * 戦士を表すドメインオブジェクト
  */
sealed abstract case class Warrior(
  id: WarriorId,
  name: String,
  attribute: Attribute,
  weapon: Option[Weapon],
  level: Level,
) extends BaseEntity[WarriorId] {

  def setNewWeapon(weapon: Weapon): Either[ConditionViolatedException, Warrior] = {
    if (isValidEquipmentCondition(weapon))
      Left(new ConditionViolatedException("属性が一緒且つ、戦士のレベルが武器の指定レベル以上である必要があります。"))
    else
      Right(new Warrior(this.id, this.name, this.attribute, Some(weapon), this.level) {})
  }

  private def isValidEquipmentCondition(weapon: Weapon): Boolean = {
    //属性が一緒か & 条件となるlevelを満たしているか
    attribute == weapon.attribute & weapon.levelConditionOfEquipment <= level.value
  }

}

object Warrior {
  def create(
    id: WarriorId,
    name: String,
    attribute: Attribute,
    level: Level,
  ): Warrior = {
    new Warrior(id, name, attribute, None, level) {}
  }
}