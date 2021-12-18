package progfun.core

import org.scalatest.funsuite.AnyFunSuite

class MowerSpec extends AnyFunSuite {

  /**
   * Test mower run on list of instructions
   */
  test("Mower should be able to run one action") {
    val land = Land(5, 5)
    val actions = List('A')
    val mower = Mower(Position(1, 2, 'N'), actions, land)
    val mowerAfterMove = mower.run
    println(mowerAfterMove)
    assert(mowerAfterMove === Mower(Position(1, 3, 'N'), actions, land))
  }

  test("Mower should be able to run a list of actions") {
    val land = Land(5, 5)
    val actions = List('G', 'A', 'G', 'A', 'G', 'A', 'G', 'A', 'A')
    val mower = Mower(Position(1, 2, 'N'), actions, land)
    val mowerAfterMove = mower.run
    println(mowerAfterMove)
    assert(mowerAfterMove === Mower(Position(1, 3, 'N'), actions, land))
  }

}
