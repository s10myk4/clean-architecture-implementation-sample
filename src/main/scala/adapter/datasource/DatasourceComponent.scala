package adapter.datasource

import adapter.datasource.onMemory.WarriorRepositoryOnMemory
import domain.lifcycle.WarriorRepository
import scalaz.Monad

import scala.concurrent.Future

trait DatasourceComponent {

  implicit def futureMonad: Monad[Future]

  lazy val warriorRepository: WarriorRepository[Future] = new WarriorRepositoryOnMemory[Future]

}
