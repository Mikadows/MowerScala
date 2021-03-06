package progfun

import progfun.io.{ConfLoader, ConfParser, FileWriter, JsonWriter}

object Main extends App {
  println("Mower program")

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

  // Build json
  val json = JsonWriter.toJson(land, mowers, movedMowers)
  println(json)
  FileWriter.write( "./output.json", json)
}
