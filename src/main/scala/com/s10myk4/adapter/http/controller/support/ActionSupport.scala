package com.s10myk4.adapter.http.controller.support

import cats.effect.IO

import scala.concurrent.{ExecutionContext, Future}

trait ActionSupport {

  def ec: ExecutionContext

  implicit class IOSyntax[A](io: IO[A]) {
    def toFuture: Future[A] = Future(io.unsafeRunSync())(ec)
  }

}
