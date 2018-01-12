package application

import scala.concurrent.Future
import scalaz.ContT

package object cont {
  type ActionCont[A, B] = ContT[Future, A, B]
}