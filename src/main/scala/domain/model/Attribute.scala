package domain.model

sealed trait Attribute

object Attribute {
  case object LightAttribute extends Attribute
  case object DarkAttribute extends Attribute
  case object WaterAttribute extends Attribute
  case object FireAttribute extends Attribute

  val attributes = Seq(
    LightAttribute,
    DarkAttribute,
    WaterAttribute,
    FireAttribute
  )
}



