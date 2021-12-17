package progfun.core

case class Mower (
  position: Position,
  actions: List[Char],
  land: Land
) {

  /**
   * Return the mower after executing all the actions
   * @return
   */
  def run: Mower = {
    this.actions
      .map(action => doAction(action))
      .map(position => Mower(position, this.actions, this.land))
      .lastOption.getOrElse(this)
  }

  /**
   * Return the mower position after executing the action
   * @param action the action to execute
   * @return the new position
   */
  private def doAction(action: Char): Position = action match {
      case 'A' => this.position.move(this.land)
      case 'G' | 'D' => this.position.turn(action)
    }

}
