package application.usecase

import domain.model.character.warrior.{Warrior, WarriorId}
import domain.model.weapon.Weapon.BlackSword
import domain.model.{DarkAttribute, Level}
import org.scalatest.{AsyncFlatSpec, Matchers}

import scala.concurrent.Future
/*
class WarriorEquippedNewWeaponSpec extends AsyncFlatSpec with Matchers {

  behavior of "戦士に新しい武器を装備する"

  it should "成功する" in {
    val warrior = Warrior(
      WarriorId(1L), "戦士A", None, DarkAttribute, Level(70)
    )

    val uc = new WarriorEquippedNewWeaponImpl
    uc.conduct(warrior, BlackSword).run(_ =>
      Future.successful(Normality)
    ).map(x => assert(x == Normality))
  }

}
*/
