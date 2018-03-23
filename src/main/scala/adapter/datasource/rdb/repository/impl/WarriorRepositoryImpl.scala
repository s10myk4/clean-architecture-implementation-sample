package adapter.datasource.rdb.repository.impl

import domain.lifcycle.WarriorRepository
import domain.model.character.warrior.{Warrior, WarriorId}
import domain.model.{Level, LightAttribute}

import scala.concurrent.Future

final class WarriorRepositoryImpl
  extends WarriorRepository[Future]
    with BaseRepositoryFutureImpl[WarriorId, Warrior]
    with ResolveFeatureRepositoryFutureImpl[WarriorId, Warrior] {

  def createEntity(id: WarriorId): Warrior = {
    Warrior.create(
      id, "戦士くん", LightAttribute, Level(50)
    )
  }
}
