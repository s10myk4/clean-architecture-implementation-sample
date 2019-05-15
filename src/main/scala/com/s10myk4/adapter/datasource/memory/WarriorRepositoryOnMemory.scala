package com.s10myk4.adapter.datasource.memory

/*
import com.s10myk4.domain.lifcycle.WarriorRepository
import com.s10myk4.domain.model.character.warrior.{Warrior, WarriorId}

import scala.concurrent.Future

final class WarriorRepositoryOnMemory
  extends WarriorRepository[State[List[Warrior], ?]] {

  type Stack = List[Warrior]

  private val stack: State[Stack, List[Warrior]] = State.init[Stack]

  override def store(entity: Warrior): State[Stack, Unit] = stack.map(_.:+(entity))

  override def existBy(id: WarriorId): State[Stack, Boolean] = stack.map(_.exists(_.id == id))

  override def resolveBy(id: WarriorId): State[Stack, Option[Warrior]] = stack.map(_.find(_.id == id))
}

object WarriorRepositoryOnMemory {
  def toFuture(repo: WarriorRepository[State[List[Warrior], ?]]): WarriorRepository[Future] = new WarriorRepository[Future] {

    override def store(entity: Warrior): Future[Unit] = Future.successful(repo.store(entity).eval(Nil))

    override def existBy(id: WarriorId): Future[Boolean] = Future.successful(repo.existBy(id).eval(Nil))

    override def resolveBy(id: WarriorId): Future[Option[Warrior]] = Future.successful(repo.resolveBy(id).eval(Nil))

  }
}
 */
