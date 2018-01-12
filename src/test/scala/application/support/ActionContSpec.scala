package application.support

import org.scalatest.AsyncFlatSpec

import scala.concurrent.{ExecutionContext, Future}
import scalaz.ContT
import scalaz.std.scalaFuture

class ActionContSpec extends AsyncFlatSpec {

  behavior of "ActionCont"

  it should "上から実行される" in {
    implicit val fm = scalaFuture.futureInstance(ExecutionContext.global)
    val res = for {
      a <- ContT[Future, String, String](f => f("a"))
      b <- ContT[Future, String, String](f => f("b"))
      c <- ContT[Future, String, String](f => f("c"))
    } yield a + b + c
    res.run_.map(x => assert(x == "abc"))
  }

  it should "処理途中で異常系が発生した場合に処理を打ち切る" in {
    implicit val fm = scalaFuture.futureInstance(ExecutionContext.global)
    val res = for {
      a <- ContT[Future, String, String](f => f("a"))
      b <- ContT[Future, String, String](f => Future.failed(new Exception("b")))
      c <- ContT[Future, String, String](f => f("c"))
    } yield a + b + c
    recoverToSucceededIf[Exception](res.run_)
  }
}


