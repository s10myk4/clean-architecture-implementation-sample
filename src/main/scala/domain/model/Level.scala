package domain.model

case class Level(value: Int) {
  require(1 <= value & value <= 99)
}
