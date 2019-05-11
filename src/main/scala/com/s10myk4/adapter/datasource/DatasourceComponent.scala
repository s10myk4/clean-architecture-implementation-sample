package com.s10myk4.adapter.datasource

import cats.effect.IO
import com.s10myk4.domain.lifcycle.WarriorRepository

trait DatasourceComponent {

  lazy val warriorRepository: WarriorRepository[IO] = ???

}


