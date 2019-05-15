package com.s10myk4.application.usecase

import com.s10myk4.domain.lifcycle.WarriorRepository
import com.s10myk4.domain.model.character.warrior.{Warrior, WarriorId, WarriorLevel, WarriorName}
import com.s10myk4.domain.model.character.warrior.WarriorArbitrary.warriorArb
import com.s10myk4.domain.model.weapon.Weapon
import com.s10myk4.domain.model.weapon.WeaponArbitrary.weaponArb
import org.scalatest.FlatSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import cats.Id
import com.s10myk4.domain.model.Attribute.LightAttribute
import com.s10myk4.domain.model.weapon.Weapon.GoldSword
import org.scalatest.mockito.MockitoSugar
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when

class EquipNewWeaponToWarriorSpec
  extends FlatSpec
    //with GeneratorDrivenPropertyChecks
    with MockitoSugar {

  behavior of "戦士に武器を装備できる"

  it should "正常系" in {
    val repository: WarriorRepository[Id] = mock[WarriorRepository[Id]]
    when(repository.store(any[Warrior])).thenReturn(())

    val warrior = (for {
      name <- WarriorName("せんしくん").toOption
      level <- WarriorLevel.apply(40).toOption
    } yield {
      Warrior.createWithoutWeapon(WarriorId(1L), name, LightAttribute, level)
    }).get

    val useCase = new EquipNewWeaponToWarrior[Id](repository)
    assert(useCase.apply(warrior, GoldSword) === NormalCase)
    /*
    forAll { (warrior: Warrior, weapon: Weapon) =>
      whenever(
        warrior.attribute == weapon.attribute &&
          weapon.levelConditionOfEquipment <= warrior.level.value
      ) {
        assert(useCase(warrior, weapon) === NormalCase)
      }
    }
     */
  }
}