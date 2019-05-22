package com.s10myk4.domain.model

import enumeratum._

sealed trait Attribute extends EnumEntry

object Attribute extends Enum[Attribute] {

  case object LightAttribute  extends Attribute
  case object DarkAttribute   extends Attribute
  case object WaterAttribute  extends Attribute
  case object FireAttribute   extends Attribute
  case object NormalAttribute extends Attribute

  val values = findValues
}
