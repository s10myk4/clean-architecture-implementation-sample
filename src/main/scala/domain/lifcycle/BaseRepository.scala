package domain.lifcycle

import domain.model.{BaseEntity, BaseEntityId}

import scala.language.higherKinds

trait BaseRepository[ID <: BaseEntityId, E <: BaseEntity[ID], F[_]] {

  def store(entity: E): F[Unit]

  def existBy(id: ID): F[Boolean]
}

trait ResolveFeatureRepository[ID <: BaseEntityId, E <: BaseEntity[ID], F[_]] {

  def resolveBy(id: ID): F[Option[E]]
}
