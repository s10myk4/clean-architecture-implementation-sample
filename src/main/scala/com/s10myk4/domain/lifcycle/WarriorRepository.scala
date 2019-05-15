package com.s10myk4.domain.lifcycle

import com.s10myk4.domain.model.character.warrior.{Warrior, WarriorId}

import scala.language.higherKinds

trait WarriorRepository[F[_]]
  extends BaseRepository[F]
    with ResolveFeatureRepository[F] {

  type ID = WarriorId
  type E = Warrior
}
