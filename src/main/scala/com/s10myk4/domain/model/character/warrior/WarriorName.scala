package com.s10myk4.domain.model.character.warrior

import cats.data.ValidatedNel
import cats.syntax.validated._

sealed abstract case class WarriorName(value: String)

object WarriorName {

  def of(value: String): ValidatedNel[WarriorError, WarriorName] = {
    val length = value.length
    if (1 <= length & length <= 20) new WarriorName(value) {}.valid
    else WarriorNameLengthError(length).invalidNel
  }

  final case class WarriorNameLengthError(length: Int) extends WarriorError {
    val cause = s"$length is a invalid warrior name size"
  }

}
