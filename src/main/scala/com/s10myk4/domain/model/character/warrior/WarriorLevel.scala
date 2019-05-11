package com.s10myk4.domain.model.character.warrior

import cats.data.ValidatedNel
import cats.syntax.validated._

sealed abstract case class WarriorLevel(value: Int)

object WarriorLevel {

  def apply(value: Int): ValidatedNel[WarriorLevelError, WarriorLevel] = {
    if (1 <= value & value <= 99) WarriorLevel(value)
    else WarriorLevelError(value).invalidNel
  }

  final case class WarriorLevelError(value: Int) extends WarriorError {
    val cause = s"$value is a invalid warrior level"
  }

}
