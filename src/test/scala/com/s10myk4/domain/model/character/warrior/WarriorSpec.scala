package com.s10myk4.domain.model.character.warrior

import com.s10myk4.domain.model.Attribute
import com.s10myk4.domain.model.character.warrior.Warrior.{DifferentAttributeError, NotOverLevelError}
import com.s10myk4.domain.model.weapon.Weapon
import org.scalatest.{EitherValues, FlatSpec, OptionValues}

class WarriorSpec extends FlatSpec with OptionValues with EitherValues {

  behavior of "equip"

  it should "装備できる" in {
    val warrior = (for {
      name  <- WarriorName.of("せんしくん").toOption
      level <- WarriorLevel.of(30).toOption
    } yield {
      Warrior.createWithoutWeapon(
        WarriorId(1),
        name,
        Attribute.LightAttribute,
        level
      )
    }).get
    assert(warrior.equip(Weapon.GoldSword).isValid)
  }

  it should "戦士の属性と武器の属性が異なる場合、装備できない" in {
    val warrior = (for {
      name  <- WarriorName.of("せんしくん").toOption
      level <- WarriorLevel.of(40).toOption
    } yield {
      Warrior.createWithoutWeapon(
        WarriorId(1),
        name,
        Attribute.LightAttribute,
        level
      )
    }).get
    assert(warrior.equip(Weapon.BlackSword).toEither.left.value.toList == List(DifferentAttributeError))
  }

  it should "戦士のレベルが武器の装備レベル条件を満たしていない場合、装備できない" in {
    val warrior = (for {
      name  <- WarriorName.of("せんしくん").toOption
      level <- WarriorLevel.of(20).toOption
    } yield {
      Warrior.createWithoutWeapon(
        WarriorId(1),
        name,
        Attribute.LightAttribute,
        level
      )
    }).get
    assert(warrior.equip(Weapon.GoldSword).toEither.left.value.toList == List(NotOverLevelError))
  }

  it should "上記2つを満たしていない場合、装備できない" in {
    val warrior = (for {
      name  <- WarriorName.of("せんしくん").toOption
      level <- WarriorLevel.of(20).toOption
    } yield {
      Warrior.createWithoutWeapon(
        WarriorId(1),
        name,
        Attribute.LightAttribute,
        level
      )
    }).get
    assert(
      warrior
        .equip(Weapon.BlackSword)
        .toEither
        .left
        .value
        .toList
        .toSet == Set(NotOverLevelError, DifferentAttributeError)
    )
  }
}
