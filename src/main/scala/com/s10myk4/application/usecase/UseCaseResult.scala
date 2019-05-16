package com.s10myk4.application.usecase

sealed trait UseCaseResult

/**
  * 正常系
  */
object NormalCase extends UseCaseResult

/**
  * 異常系
  */
trait AbnormalCase extends UseCaseResult {
  val cause: String
}

object NotConsideredDomainError extends AbnormalCase {
  val cause = "This domain error is not considered"
}

/**
  * エンティティが存在しない
  */
trait EntityNotFound extends AbnormalCase

/**
  *  エンティティが重複
  */
trait EntityDuplicated extends AbnormalCase

/**
  * 不正な入力値
  */
final case class InvalidInputParameters(cause: String = "Invalid input parameters", errors: Map[String, String])
    extends AbnormalCase
