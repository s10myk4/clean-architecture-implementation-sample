package com.s10myk4.application.usecase

import com.s10myk4.application.cont.UseCaseCont
import com.s10myk4.application.support.UseCaseCont
import com.s10myk4.application.usecase.FindWarrior.WarriorNotFound
import com.s10myk4.domain.lifcycle.WarriorRepository
import com.s10myk4.domain.model.character.warrior.{Warrior, WarriorId}
import cats.Monad
import cats.syntax.flatMap._

import scala.language.higherKinds

/**
  * 戦士を取得する
  */
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

  case object WarriorNotFound extends EntityNotFound {
    val cause: String = "A warrior do not found."
  }

}
