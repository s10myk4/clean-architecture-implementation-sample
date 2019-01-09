package com.s10myk4.domain.model.exception

/**
  * ビジネスの制約に違反しているという例外
  */
final class ConditionViolatedException(message: String) extends Exception(message)
