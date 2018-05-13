package domain.lifcycle

import domain.model.character.warrior.{Warrior, WarriorId}

import scala.concurrent.Future
import scala.language.higherKinds

trait WarriorRepository[F[_]]
  extends BaseRepository[WarriorId, Warrior, F]
    with ResolveFeatureRepository[WarriorId, Warrior, F]

object WarriorRepository {
  implicit val futureRepository: WarriorRepository[Future] = new WarriorRepository[Future] {

    private var db = scala.collection.mutable.Map.empty[WarriorId, Warrior]

    override def store(entity: Warrior): Future[Unit] = {
      Future.successful(db += entity.id -> entity)
    }

    override def existBy(id: WarriorId): Future[Boolean] =
      Future.successful(db.isDefinedAt(id))


    override def resolveBy(id: WarriorId): Future[Option[Warrior]] =
      Future.successful(db.get(id))
  }
}
