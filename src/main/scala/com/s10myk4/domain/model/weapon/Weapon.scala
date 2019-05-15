package com.s10myk4.domain.model.weapon

import com.s10myk4.domain.model.Attribute.{DarkAttribute, LightAttribute}
import com.s10myk4.domain.model._

/**
  * 武器を表すドメインオブジェクト
  */
sealed trait Weapon {
  val name: String
  val offensivePower: Int
  val attribute: Attribute
  val levelConditionOfEquipment: Int
}

object Weapon {

  case object GoldSword extends Weapon {
    val name: String                   = "gold sword"
    val offensivePower: Int            = 44
    val attribute: Attribute           = LightAttribute
    val levelConditionOfEquipment: Int = 30
  }

  case object BlackSword extends Weapon {
    val name: String                   = "black sword"
    val offensivePower: Int            = 50
    val attribute: Attribute           = DarkAttribute
    val levelConditionOfEquipment: Int = 40
  }

  val weapons = Seq(
    GoldSword,
    BlackSword
  )

  def indexOf(str: String): Option[Weapon] = str match {
    case "goldSword"  => Some(GoldSword)
    case "blackSword" => Some(BlackSword)
    case _            => None
  }
}
