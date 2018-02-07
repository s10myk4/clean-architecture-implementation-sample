package application.usecase

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
final case class InvalidInputParameters(cause: String = "不正な入力値です", errors: Map[String, String]) extends AbnormalCase