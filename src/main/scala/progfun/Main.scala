package progfun

import progfun.io.{ConfLoader, ConfParser}

object Main extends App {
  println("Mower program")
  // Load configuration
  val config = ConfLoader.load("./config.txt")
  // Get Land from configuration
  val land = ConfParser.getLand(config)
  println(land)
}
