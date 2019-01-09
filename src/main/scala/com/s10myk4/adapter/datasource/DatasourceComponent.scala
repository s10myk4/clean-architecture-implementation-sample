package com.s10myk4.adapter.datasource

import com.s10myk4.adapter.datasource.onMemory.WarriorRepositoryOnMemory
import com.s10myk4.domain.lifcycle.WarriorRepository
import scalaz.Monad

import scala.concurrent.Future

trait DatasourceComponent {

  implicit def futureMonad: Monad[Future]

  lazy val warriorRepository: WarriorRepository[Future] = new WarriorRepositoryOnMemory[Future]

}
