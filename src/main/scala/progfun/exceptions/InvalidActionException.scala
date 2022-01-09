package progfun.exceptions

case class InvalidActionException(msg: String) extends RuntimeException(msg)
