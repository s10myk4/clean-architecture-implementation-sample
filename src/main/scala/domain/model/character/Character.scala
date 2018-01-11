package domain.model.character

import domain.model.{BaseEntity, BaseEntityId}

trait Character[ID <: BaseEntityId] extends BaseEntity[ID]
