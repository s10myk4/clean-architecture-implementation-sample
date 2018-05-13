package domain.model.character.warrior

import domain.model.{Attribute, Level}
import org.scalacheck.{Arbitrary, Gen}

object WarriorArbitrary {
  implicit def warriorArb: Arbitrary[Warrior] = Arbitrary(WarriorGens.defaultGen)
}

object WarriorGens {

  private val idGen = Gen.posNum[Long].map(WarriorId)
  private val levelGen = Gen.chooseNum(40, 99).map(Level)
  val defaultGen: Gen[Warrior] = for {
    id <- idGen
    name <- Gen.asciiPrintableStr
    attribute <- Gen.oneOf(Attribute.attributes)
    level <- levelGen
  } yield Warrior.createWithoutWeapon(
    id,
    name,
    attribute,
    level
  )
}
