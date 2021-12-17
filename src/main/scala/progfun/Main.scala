package progfun

import progfun.io.{ConfLoader, ConfParser, JsonWriter}

object Main extends App {
  println("Mower program")

  /**
   * May refactor to use for comprehension
   */

  // Load configuration
  val config = ConfLoader.load("./config.txt")
  // Get Land from configuration
  val land = ConfParser.getLand(config)
  // Get Mowers from configuration
  val mowers = ConfParser.getMowers(config, land)
  mowers.foreach(m => println(m.toString))

  // Run mowers
  val movedMowers = mowers.map(mower => mower.run)
  println(movedMowers)

  // Print json
  val json = JsonWriter.toJson(land, mowers, movedMowers)
  println(json)
}
