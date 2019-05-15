package com.s10myk4.domain.lifcycle

import com.s10myk4.domain.model.{BaseEntity, BaseEntityId}

import scala.language.higherKinds

trait BaseRepository[F[_]] {

  type ID <: BaseEntityId

  type E <: BaseEntity[ID]

  def store(entity: E): F[Unit]

  def update(entity: E): F[Unit]

  def existBy(id: ID): F[Boolean]
}

trait ResolveFeatureRepository[F[_]] {

  type ID <: BaseEntityId

  type E <: BaseEntity[ID]

  def resolveBy(id: ID): F[Option[E]]
}
