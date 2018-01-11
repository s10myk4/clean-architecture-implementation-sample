package domain.model

sealed trait Attribute

case object LightAttribute extends Attribute

case object DarkAttribute extends Attribute

case object WaterAttribute extends Attribute

case object FireAttribute extends Attribute
