package application.usecase

import application.cont.ActionCont
import application.support.ActionCont
import domain.lifcycle.{IOContext, WarriorRepository}
import domain.model.character.warrior.{Warrior, WarriorId}

import scala.concurrent.Future

trait IdentifyWarrior {
  def conduct(id: WarriorId): ActionCont[Warrior]

  case object WarriorNotFound extends EntityNotFound {
    val cause: String = "A warrior do not found."
  }

}

final class IdentifyWarriorImpl[Ctx <: IOContext](
  ctx: Ctx,
  repository: WarriorRepository[Future]
) extends IdentifyWarrior {
  def conduct(id: WarriorId): ActionCont[Warrior] =
    ActionCont { f =>
      repository.resolveBy(id).flatMap {
        case Some(e) => f(e)
        case None => Future.successful(WarriorNotFound)
      }(ctx.ec)
    }
}
