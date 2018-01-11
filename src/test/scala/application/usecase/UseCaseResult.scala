package application.usecase

sealed trait UseCaseResult

/**
  * 正常系
  */
case object Normality extends UseCaseResult

/**
  * 異常系
  */
trait Abnormality extends UseCaseResult {
  val cause: String
}

trait EntityNotFound extends Abnormality

trait EntityDuplicated extends Abnormality

final case class InvalidInputParameters(cause: String = "不正なフォームの入力です", errors: Map[String, String]) extends Abnormality
