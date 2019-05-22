package com.s10myk4.application.usecase

import cats.Monad
import cats.syntax.flatMap._
import com.s10myk4.application.cont.UseCaseCont
import com.s10myk4.application.support.UseCaseCont
import com.s10myk4.application.usecase.FindWarrior.WarriorNotFound
import com.s10myk4.domain.lifcycle.WarriorRepository
import com.s10myk4.domain.model.character.warrior.{Warrior, WarriorId}

import scala.language.higherKinds

final class FindWarrior[F[_]: Monad](
    repository: WarriorRepository[F]
) {

  def exec(id: WarriorId): UseCaseCont[F, Warrior] = UseCaseCont { f =>
    repository.resolveBy(id).flatMap {
      case Some(w) => f(w)
      case None    => Monad[F].point(WarriorNotFound)
    }
  }

}

object FindWarrior {

  case object WarriorNotFound extends AbnormalCase {
    val cause: String = "A warrior do not found."
  }

}
