package application

import scalaz.Kleisli

import scala.concurrent.Future

package object IoCtx {

  type DBSession

  type IoCtx[A] = Kleisli[Future, DBSession, A]

}
