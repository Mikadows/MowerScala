package progfun.io

import progfun.core.{Land, Mower, Position}
import scala.annotation.tailrec

object ConfParser {

  /**
   * Parse a configuration and return the Land.
   * @param conf the configuration to parse as list of strings for each line.
   * @return Land
   */
  def getLand(conf: List[String]): Land = {
    val landLine = conf(0).split(" ")
    Land(landLine(0).toInt, landLine(1).toInt)
  }

  /**
   * Parse the configuration and return the list of Mowers.
   * @param conf the configuration to parse as list of strings for each line.
   * @param land the Land to use by the Mowers.
   * @return List[Mower]
   */
  def getMowers(conf: List[String], land: Land): List[Mower] = {
    mowerConfParser(conf.drop(1), land, List())
  }

  /**
   * Parse the mower with recursive call.
   * @param conf the configuration to parse as list of strings for each line.
   * @param land the Land to use by the Mowers.
   * @param acc the list of Mowers to return.
   * @return List[Mower]
   */
  @tailrec
  private def mowerConfParser(conf: List[String], land: Land, acc: List[Mower]): List[Mower] = conf match {
    case Nil => acc
    case position :: actions =>
      val posRow = position.split(" ")
      val pos = Position(posRow(0).toInt, posRow(1).toInt, posRow(2).charAt(0))
      val actionList = actions(0).toList
      val mower = Mower(pos, actionList, land)
      mowerConfParser(actions.drop(1), land, mower :: acc)
  }

}
