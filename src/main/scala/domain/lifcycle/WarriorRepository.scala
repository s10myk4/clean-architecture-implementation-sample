package domain.lifcycle

import domain.model.character.warrior.{Warrior, WarriorId}

import scala.language.higherKinds

trait WarriorRepository[F[_]]
  extends BaseRepository[WarriorId, Warrior, F]
  with ResolveFeatureRepository[WarriorId, Warrior, F]
