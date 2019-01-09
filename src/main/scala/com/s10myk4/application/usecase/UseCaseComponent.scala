package com.s10myk4.application.usecase

import com.s10myk4.adapter.datasource.DatasourceComponent
import scalaz.Monad

import scala.concurrent.Future

trait UseCaseComponent {
  _: DatasourceComponent =>

  implicit def futureMonad: Monad[Future]

  lazy val equipNewWeaponToWarrior: EquipNewWeaponToWarrior[Future] = new EquipNewWeaponToWarrior[Future](warriorRepository)

  lazy val findWarrior: FindWarrior[Future] = new FindWarrior[Future](warriorRepository)

}
