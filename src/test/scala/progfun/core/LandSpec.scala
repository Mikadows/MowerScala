package progfun.core

import org.scalatest.funsuite.AnyFunSuite

class LandSpec extends AnyFunSuite {

  test("Nominal land creation") {
    val land = Land(2, 2)
    assert(land.xSize == 2)
    assert(land.ySize == 2)
  }

  test("Test valid if position is valid in the land nominal case 1") {
    val land = Land(2, 2)
    assert(land.isValidPosition(Position(0, 0, 'W')))
  }

  test("Test valid if position is valid in the land nominal case 2") {
    val land = Land(2, 2)
    assert(land.isValidPosition(Position(1, 1, 'W')))
  }

  test("Test valid if position is valid in the land nominal false position") {
    val land = Land(2, 2)
    assert(!land.isValidPosition(Position(2, 1, 'W')))
  }
}
