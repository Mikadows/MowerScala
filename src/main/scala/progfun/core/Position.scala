package progfun.core

/**
 * Position of a Mower in the land.
 * @param x X coordinate of the position.
 * @param y Y coordinate of the position.
 * @param direction Direction of the mower.
 */
case class Position (x: Int, y: Int, direction: Char) {

  /**
   * Move the mower in the direction it is facing.
   * @param instruction Instruction to move the mower.
   * @return New position of the mower.
   */
  def turn(instruction: Char) : Position = {
    this.direction match {
      case 'N' => instruction match {
        case 'G' => Position(this.x, this.y, 'W')
        case 'D' => Position(this.x, this.y, 'E')
      }
      case 'E' => instruction match {
        case 'G' => Position(this.x, this.y, 'N')
        case 'D' => Position(this.x, this.y, 'S')
      }
      case 'S' => instruction match {
        case 'G' => Position(this.x, this.y, 'E')
        case 'D' => Position(this.x, this.y, 'W')
      }
      case 'W' => instruction match {
        case 'G' => Position(this.x, this.y, 'S')
        case 'D' => Position(this.x, this.y, 'N')
      }
      // TODO: Exception throw
    }
  }

  /**
   * Move the mower in the direction it is facing.
   * @param land Land where the mower is.
   * @return New position of the mower.
   */
  def move(land: Land): Position = {
    val newPosition = this.direction match {
      case 'N' => Position(this.x, this.y + 1, this.direction)
      case 'E' => Position(this.x + 1, this.y, this.direction)
      case 'S' => Position(this.x, this.y - 1, this.direction)
      case 'W' => Position(this.x - 1, this.y, this.direction)
    }
    if (land.isValidPosition(newPosition)) {
      newPosition
    } else {
      this
    }
  }
}
