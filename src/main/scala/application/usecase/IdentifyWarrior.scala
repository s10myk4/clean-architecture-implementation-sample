package application.usecase

import application.cont.ActionCont
import application.support.ActionCont
import domain.lifcycle.exception.EntityNotFoundException
import domain.lifcycle.{IOContext, WarriorRepository}
import domain.model.character.warrior.{Warrior, WarriorId}

import scala.concurrent.Future

trait IdentifyWarrior {
  def conduct(id: WarriorId): ActionCont[UseCaseResult, Warrior]
}

final class IdentifyWarriorImpl[Ctx <: IOContext](
  ctx: Ctx,
  repository: WarriorRepository[Future]
) extends IdentifyWarrior {
  def conduct(id: WarriorId): ActionCont[UseCaseResult, Warrior] =
    ActionCont { f =>
      repository.resolveBy(id).flatMap {
        case Some(e) => f(e)
        case None => Future.failed(new EntityNotFoundException(s"a warrior do not found. id: " + id.value))
      }(ctx.ec)
    }
}
