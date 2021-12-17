package progfun.core

case class Mower (
  position: Position,
  actions: List[Char],
  land: Land
) {

  def run: Mower = {
    this.actions
      .map(action => doAction(action))
      .map(position => Mower(position, this.actions, this.land))
      .lastOption.getOrElse(this)
  }

  def doAction(action: Char): Position = action match {
      case 'A' => this.position.move
      case 'G' | 'D' => this.position.turn(action)
    }

}
