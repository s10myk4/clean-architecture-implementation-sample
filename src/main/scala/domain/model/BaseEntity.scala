package domain.model

trait BaseEntity[ID <: BaseEntityId] {
  val id: ID
}
