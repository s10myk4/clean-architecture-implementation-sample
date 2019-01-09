package com.s10myk4.domain.model

trait BaseEntity[ID <: BaseEntityId] {
  val id: ID
}
