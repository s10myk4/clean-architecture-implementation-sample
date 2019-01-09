package adapter.datasource.onMemory

import domain.lifcycle.WarriorRepository
import domain.model.character.warrior.{Warrior, WarriorId}
import scalaz.Monad

import scala.language.higherKinds

final class WarriorRepositoryOnMemory[F[_]: Monad]
  extends WarriorRepository[F] {

  private var db = scala.collection.mutable.Map.empty[WarriorId, Warrior]

  override def store(entity: Warrior): F[Unit] = {
    Monad[F].point(db += entity.id -> entity)
  }

  override def existBy(id: WarriorId): F[Boolean] =
    Monad[F].point(db.isDefinedAt(id))


  override def resolveBy(id: WarriorId): F[Option[Warrior]] =
    Monad[F].point(db.get(id))
}
