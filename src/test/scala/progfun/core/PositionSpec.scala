package progfun.core

import org.scalatest.funsuite.AnyFunSuite

class PositionSpec extends AnyFunSuite {

  test("Position creation") {
    val pos = Position(1, 2, 'N')
    assert(pos.x == 1)
    assert(pos.y == 2)
    assert(pos.direction == 'N')
  }

  /**
   * Test turn when facing north
   */
  test("Position turn left with N") {
    val pos = Position(1, 2, 'N')
    assert(pos.turn('G').direction == 'W')
  }

  test("Position turn right with N") {
    val pos = Position(1, 2, 'N')
    assert(pos.turn('D').direction == 'E')
  }

  /**
   * Test turn when facing east
   */
  test("Position turn left with E") {
    val pos = Position(1, 2, 'E')
    assert(pos.turn('G').direction == 'N')
  }

  test("Position turn right with E") {
    val pos = Position(1, 2, 'E')
    assert(pos.turn('D').direction == 'S')
  }

  /**
   * Test turn when facing south
   */
  test("Position turn left with S") {
    val pos = Position(1, 2, 'S')
    assert(pos.turn('G').direction == 'E')
  }

  test("Position turn right with S") {
    val pos = Position(1, 2, 'S')
    assert(pos.turn('D').direction == 'W')
  }

  /**
   * Test turn when facing west
   */
  test("Position turn left with W") {
    val pos = Position(1, 2, 'W')
    assert(pos.turn('G').direction == 'S')
  }

  test("Position turn right with W") {
    val pos = Position(1, 2, 'W')
    assert(pos.turn('D').direction == 'N')
  }

  /**
   * Test move forward when facing north
   */
  test("Position move forward with N") {
    val land = Land(5, 5)
    val pos = Position(1, 2, 'N')
    assert(pos.move(land).y == 3)
  }

  /**
   * Test move forward when facing east
   */
  test("Position move forward with E") {
    val land = Land(5, 5)
    val pos = Position(2, 2, 'E')
    assert(pos.move(land).x == 3)
  }

  /**
   * Test move forward when facing south
   */
  test("Position move forward with S") {
    val land = Land(5, 5)
    val pos = Position(2, 2, 'S')
    assert(pos.move(land).y == 1)
  }

  /**
   * Test move forward when facing west
   */
  test("Position move forward with W") {
    val land = Land(5, 5)
    val pos = Position(2, 2, 'W')
    assert(pos.move(land).x == 1)
  }

  /**
   * Test move forward when facing north and hitting a wall
   */
  test("Position move forward with N and hitting a wall") {
    val land = Land(5, 5)
    val pos = Position(5, 5, 'N')
    assert(pos.move(land).x == 5)
    assert(pos.move(land).y == 5)
    assert(pos.move(land).direction == 'N')
  }

  /**
   * Test move forward when facing south and hitting a wall
   */
  test("Position move forward with S and hitting a wall") {
    val land = Land(5, 5)
    val pos = Position(0, 0, 'S')
    assert(pos.move(land).x == 0)
    assert(pos.move(land).y == 0)
    assert(pos.move(land).direction == 'S')
  }

  /**
   * Test move forward when facing east and hitting a wall
   */
  test("Position move forward with E and hitting a wall") {
    val land = Land(5, 5)
    val pos = Position(5, 0, 'E')
    assert(pos.move(land).x == 5)
    assert(pos.move(land).y == 0)
    assert(pos.move(land).direction == 'E')
  }

  /**
   * Test move forward when facing west and hitting a wall
   */
  test("Position move forward with W and hitting a wall") {
    val land = Land(5, 5)
    val pos = Position(0, 5, 'W')
    assert(pos.move(land).x == 0)
    assert(pos.move(land).y == 5)
    assert(pos.move(land).direction == 'W')
  }
}
