package application.usecase

import application.cont.ActionCont
import application.support.ActionContSpec
import domain.model.character.warrior.Warrior
import domain.model.weapon.Weapon

import scala.concurrent.Future
import scalaz.{-\/, \/-}

trait WarriorEquippedNewWeapon {
  def conduct(warrior: Warrior, newWeapon: Weapon): ActionCont[Warrior]

  case object InvalidCondition extends Abnormality {
    val cause: String = "この武器を装備するための条件を満たしていません"
  }

}

final class WarriorEquippedNewWeaponImpl extends WarriorEquippedNewWeapon {
  def conduct(warrior: Warrior, newWeapon: Weapon): ActionCont[Warrior] =
    ActionContSpec { f =>
      warrior.setNewWeapon(newWeapon) match {
        case \/-(w) => f(w)
        case -\/(_) => Future.successful(InvalidCondition)
      }
    }
}
