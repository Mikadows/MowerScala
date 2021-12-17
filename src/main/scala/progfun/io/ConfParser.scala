package progfun.io

import progfun.core.{Land, Mower, Position}

object ConfParser {

  /**
   * Parse a configuration and return the Land.
   * @param conf
   * @return Land
   */
  def getLand(conf: List[String]): Land = {
    val landLine = conf(0).split(" ")
    Land(landLine(0).toInt, landLine(1).toInt)
  }

  // Get mowers from the configuration
  def getMowers(conf: List[String], land: Land): List[Mower] = {
    mowerConfParser(conf.drop(1), land, List())
  }

  def mowerConfParser(conf: List[String], land: Land, acc: List[Mower]): List[Mower] = conf match {
    case Nil => acc
    case position :: actions =>
      val posRow = position.split(" ")
      val pos = Position(posRow(0).toInt, posRow(1).toInt, posRow(2).charAt(0))
      val actionList = actions(0).toList
      println("Actions "+ actionList.toString())
      val mower = new Mower(pos, actionList, land)
      mowerConfParser(actions.drop(1), land, mower :: acc)
  }

}
