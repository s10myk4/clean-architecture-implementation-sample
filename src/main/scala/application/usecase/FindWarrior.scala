package application.usecase

import application.cont.ActionCont
import application.support.ActionCont
import application.usecase.FindWarrior.WarriorNotFound
import domain.lifcycle.WarriorRepository
import domain.model.character.warrior.{Warrior, WarriorId}
import scalaz.Monad
import scalaz.syntax.monad._

import scala.language.higherKinds

/**
  * 戦士を取得する
  */
final class FindWarrior[F[_] : Monad](
  repository: WarriorRepository[F]
) {

  private val F: Monad[F] = implicitly

  def apply(id: WarriorId): ActionCont[F, Warrior] = {
    ActionCont { f =>
      repository.resolveBy(id).flatMap {
        case Some(w) => f(w)
        case None => F.point(WarriorNotFound)
      }
    }
  }
}

object FindWarrior {

  case object WarriorNotFound extends EntityNotFound {
    val cause: String = "A warrior do not found."
  }

}
