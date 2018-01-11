package domain.model.weapon

import domain.model._

sealed trait Weapon {
  val name: String
  val offensivePower: Int
  val attribute: Attribute
  val levelConditionOfEquipment: Int
}

object Weapon {

  case object GoldSword extends Weapon {
    val name: String = "gold sword"
    val offensivePower: Int = 44
    val attribute: Attribute = LightAttribute
    val levelConditionOfEquipment: Int = 30
  }

  case object BlackSword extends Weapon {
    val name: String = "black sword"
    val offensivePower: Int = 50
    val attribute: Attribute = DarkAttribute
    val levelConditionOfEquipment: Int = 40
  }

  def fromString(str: String): Weapon = str match {
    case "goldSword" => GoldSword
    case "blackSword"    => BlackSword
    case _              => throw new IllegalArgumentException(s"$str did not match.")
  }
}
