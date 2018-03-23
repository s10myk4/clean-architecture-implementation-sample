import adapter.datasource.rdb.repository.impl.WarriorRepositoryImpl
import domain.lifcycle.WarriorRepository

import scala.concurrent.Future
import com.softwaremill.macwire.wire

trait RepositoryModule {

  lazy val warriorRepository: WarriorRepository[Future] = wire[WarriorRepositoryImpl]

}
