package application.usecase

import application.cont.ActionCont
import application.support.ActionCont
import domain.lifcycle.{IOContext, WarriorRepository}
import domain.model.character.warrior.{Warrior, WarriorId}

import scala.concurrent.Future

/**
  * 戦士を取得する
  */
trait FindWarrior {
  def apply(id: WarriorId): ActionCont[Warrior]

  case object WarriorNotFound extends EntityNotFound {
    val cause: String = "A warrior do not found."
  }

}

final class FindWarriorImpl[Ctx <: IOContext](
  ctx: Ctx,
  repository: WarriorRepository[Future]
) extends FindWarrior {
  def apply(id: WarriorId): ActionCont[Warrior] = {
    ActionCont { f =>
      repository.resolveBy(id).flatMap {
        case Some(w) => f(w)
        case None => Future.successful(WarriorNotFound)
      }(ctx.ec)
    }
  }
}
