package progfun.core

case class Land(xSize: Int, ySize: Int) {
  def isValidPosition(position: Position): Boolean = {
    (
      (position.x >= 0 && position.x < xSize)
      &&
      (position.y >= 0 && position.y < ySize)
    )
  }
}
