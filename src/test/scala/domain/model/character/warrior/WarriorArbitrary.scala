package domain.model.character.warrior

import domain.model.Attribute
import org.scalacheck.{Arbitrary, Gen}

object WarriorArbitrary {
  implicit def warriorArb: Arbitrary[Warrior] = Arbitrary(WarriorGens.defaultGen)
}

object WarriorGens {

  private val idGen = Gen.posNum[Long].map(WarriorId)
  private val warriorNameGen = Gen.asciiPrintableStr.map(WarriorName)
  private val levelGen = Gen.chooseNum(40, 99).map(WarriorLevel)
  val defaultGen: Gen[Warrior] = for {
    id <- idGen
    name <- warriorNameGen
    attribute <- Gen.oneOf(Attribute.attributes)
    level <- levelGen
  } yield Warrior.createWithoutWeapon(
    id,
    name,
    attribute,
    level
  )
}
