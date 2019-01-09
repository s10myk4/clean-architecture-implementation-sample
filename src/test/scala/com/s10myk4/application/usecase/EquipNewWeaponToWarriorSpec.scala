package com.s10myk4.application.usecase

import com.s10myk4.domain.lifcycle.WarriorRepository
import com.s10myk4.domain.model.character.warrior.Warrior
import com.s10myk4.domain.model.character.warrior.WarriorArbitrary._
import com.s10myk4.domain.model.weapon.Weapon
import com.s10myk4.domain.model.weapon.WeaponArbitrary._
import org.scalatest.FlatSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import scalaz.Id.Id

class EquipNewWeaponToWarriorSpec
  extends FlatSpec
    with GeneratorDrivenPropertyChecks {

  behavior of "戦士に新しい武器を装備する"

  it should "成功する" in {
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