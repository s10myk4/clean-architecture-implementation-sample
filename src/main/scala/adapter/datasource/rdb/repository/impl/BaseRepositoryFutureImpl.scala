package adapter.datasource.rdb.repository.impl

import domain.lifcycle.{BaseRepository, ResolveFeatureRepository}
import domain.model.{BaseEntity, BaseEntityId}

import scala.concurrent.Future

trait BaseRepositoryFutureImpl[ID <: BaseEntityId, E <: BaseEntity[ID]]
  extends BaseRepository[ID, E, Future] {

  def store(entity: E): Future[Unit] = Future.successful(())

  def existBy(id: ID): Future[Boolean] = Future.successful(true)
}

trait ResolveFeatureRepositoryFutureImpl[ID <: BaseEntityId, E <: BaseEntity[ID]]
  extends ResolveFeatureRepository[ID, E, Future] {

  def createEntity(id: ID): E

  def resolveBy(id: ID): Future[Option[E]] = Future.successful(
    Some(createEntity(id))
  )
}