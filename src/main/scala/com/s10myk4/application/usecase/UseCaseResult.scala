package com.s10myk4.application.usecase

sealed trait UseCaseResult

object NormalCase extends UseCaseResult

trait AbnormalCase extends UseCaseResult {
  val cause: String
}

object NotConsideredDomainError extends AbnormalCase {
  val cause = "This domain error is not considered in this UseCase"
}

final case class InvalidInputParameters(cause: String = "Invalid input parameters", errors: Map[String, String])
    extends AbnormalCase
