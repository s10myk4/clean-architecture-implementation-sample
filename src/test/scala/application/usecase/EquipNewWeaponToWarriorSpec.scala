package application.usecase

import domain.lifcycle.WarriorRepository
import domain.model.character.warrior.Warrior
import domain.model.character.warrior.WarriorArbitrary._
import domain.model.weapon.Weapon
import domain.model.weapon.WeaponArbitrary._
import org.scalatest.FlatSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import scalaz.Id.Id
import scalaz.std.scalaFuture

import scala.concurrent.ExecutionContext

class EquipNewWeaponToWarriorSpec
  extends FlatSpec
    with GeneratorDrivenPropertyChecks {

  behavior of "戦士に新しい武器を装備する"

  it should "成功する" in {
    implicit val fm = scalaFuture.futureInstance(ExecutionContext.global)

    val repository: WarriorRepository[Id] = ???

    val useCase = new EquipNewWeaponToWarrior[Id](repository)
    forAll { (warrior: Warrior, weapon: Weapon) =>
      whenever(
        warrior.attribute == weapon.attribute &&
          weapon.levelConditionOfEquipment <= warrior.level.value
      ) {
        assert(useCase(warrior, weapon) === NormalCase)
      }
    }
  }
}