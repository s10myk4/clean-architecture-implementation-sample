package domain.model.character.warrior

case class WarriorLevel(value: Int) {
  require(1 <= value & value <= 99)
}
