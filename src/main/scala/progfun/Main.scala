package progfun

import progfun.io.{ConfLoader, ConfParser}

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


}
