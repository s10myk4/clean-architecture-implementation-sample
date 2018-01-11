package application.usecase

import application.cont.ActionCont
import application.support.ActionCont
import domain.lifcycle.{IOContext, WarriorRepository}
import domain.lifcycle.exception.EntityDuplicatedException
import domain.model.character.warrior.Warrior

import scala.concurrent.Future

trait RegisterWarrior {
  def conduct(warrior: Warrior): ActionCont[UseCaseResult]

  case object WarriorDuplicated extends EntityDuplicated {
    val cause: String = "A warrior duplicated."
  }
}

final class RegisterWarriorImpl[Ctx <: IOContext](
  ctx: Ctx,
  repository: WarriorRepository[Future]
) extends RegisterWarrior {
  def conduct(warrior: Warrior): ActionCont[UseCaseResult] =
    ActionCont { f =>
      repository.store(warrior).flatMap(_ => f(Normality))(ctx.ec)
        .recoverWith {
          case _: EntityDuplicatedException => Future.successful(WarriorDuplicated)
        }(ctx.ec)
    }
}
