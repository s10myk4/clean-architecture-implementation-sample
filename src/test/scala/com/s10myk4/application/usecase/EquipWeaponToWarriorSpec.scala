package com.s10myk4.application.usecase

import cats.{Applicative, Id}
import com.s10myk4.application.usecase.EquipWeaponToWarrior.{
  DifferentAttribute,
  DifferentAttributeAndNotOverLevel,
  NotOverLevel
}
import com.s10myk4.domain.lifcycle.WarriorRepository
import com.s10myk4.domain.model.Attribute.{DarkAttribute, LightAttribute}
import com.s10myk4.domain.model.character.warrior.{Warrior, WarriorId, WarriorLevel, WarriorName}
import com.s10myk4.domain.model.weapon.Weapon.GoldSword
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{DiagrammedAssertions, FlatSpec}

class EquipWeaponToWarriorSpec extends FlatSpec with DiagrammedAssertions with MockitoSugar {

  behavior of "戦士に武器を装備できる"

  it should "正常系" in {
    val warrior = (for {
      name  <- WarriorName.of("せんしくん").toOption
      level <- WarriorLevel.of(40).toOption
    } yield {
      Warrior.createWithoutWeapon(WarriorId(1L), name, LightAttribute, level)
    }).get

    assert(useCase.exec(warrior, GoldSword).run(Applicative[Id].pure) === NormalCase)
  }

  it should "異常系: 戦士のレベルが選択した武器のレベル条件を満たしていない場合" in {
    val warrior = (for {
      name  <- WarriorName.of("せんしくん").toOption
      level <- WarriorLevel.of(29).toOption
    } yield {
      Warrior.createWithoutWeapon(WarriorId(1L), name, LightAttribute, level)
    }).get

    assert(
      useCase.exec(warrior, GoldSword).run(Applicative[Id].pure) === NotOverLevel
    )
  }

  it should "異常系: 戦士の属性と選択した武器の属性が異なる場合" in {
    val warrior = (for {
      name  <- WarriorName.of("せんしくん").toOption
      level <- WarriorLevel.of(40).toOption
    } yield {
      Warrior.createWithoutWeapon(WarriorId(1L), name, DarkAttribute, level)
    }).get

    assert(
      useCase.exec(warrior, GoldSword).run(Applicative[Id].pure) === DifferentAttribute
    )
  }

  it should "異常系: 戦士のレベルが選択した武器のレベル条件を満たしていない、かつ、戦士の属性と選択した武器の属性が異なる場合" in {
    val warrior = (for {
      name  <- WarriorName.of("せんしくん").toOption
      level <- WarriorLevel.of(29).toOption
    } yield {
      Warrior.createWithoutWeapon(WarriorId(1L), name, DarkAttribute, level)
    }).get

    assert(
      useCase.exec(warrior, GoldSword).run(Applicative[Id].pure) ===
        DifferentAttributeAndNotOverLevel
    )
  }

  private val repository: WarriorRepository[Id] = mock[WarriorRepository[Id]]
  when(repository.update(any[Warrior])).thenReturn(())

  private val useCase = new EquipWeaponToWarrior[Id](repository)

}
