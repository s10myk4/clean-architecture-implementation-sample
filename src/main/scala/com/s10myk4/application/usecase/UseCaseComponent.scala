package com.s10myk4.application.usecase

import cats.effect.IO
import com.s10myk4.adapter.datasource.DatasourceComponent

trait UseCaseComponent { _: DatasourceComponent =>

  lazy val equipNewWeaponToWarrior: EquipWeaponToWarrior[IO] =
    new EquipWeaponToWarrior[IO](warriorRepository)

  lazy val findWarrior: FindWarrior[IO] =
    new FindWarrior[IO](warriorRepository)

}
