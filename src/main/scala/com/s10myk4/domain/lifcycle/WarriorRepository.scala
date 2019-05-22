package com.s10myk4.domain.lifcycle

import com.s10myk4.domain.model.character.warrior.{Warrior, WarriorId}

import scala.language.higherKinds

trait WarriorRepository[F[_]] {
  def update(warrior: Warrior): F[Unit]
  def resolveBy(id: WarriorId): F[Option[Warrior]]
}
