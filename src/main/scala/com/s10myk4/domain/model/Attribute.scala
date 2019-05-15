package com.s10myk4.domain.model

sealed trait Attribute

object Attribute {
  case object LightAttribute extends Attribute
  case object DarkAttribute extends Attribute
  case object WaterAttribute extends Attribute
  case object FireAttribute extends Attribute
  case object NormalAttribute extends Attribute

  val attributes = Seq(
    LightAttribute,
    DarkAttribute,
    WaterAttribute,
    FireAttribute,
    NormalAttribute
  )
}



