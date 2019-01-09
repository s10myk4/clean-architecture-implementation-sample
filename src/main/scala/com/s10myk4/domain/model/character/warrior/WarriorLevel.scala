package com.s10myk4.domain.model.character.warrior

case class WarriorLevel(value: Int) {
  require(1 <= value & value <= 99)
}
