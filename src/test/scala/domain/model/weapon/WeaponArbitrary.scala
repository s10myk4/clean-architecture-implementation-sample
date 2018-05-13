package domain.model.weapon

import org.scalacheck.{Arbitrary, Gen}

object WeaponGens {
  val defaultGen: Gen[Weapon] = Gen.oneOf(Weapon.weapons)
}

object WeaponArbitrary {
  implicit def weaponArb: Arbitrary[Weapon] = Arbitrary(WeaponGens.defaultGen)
}
