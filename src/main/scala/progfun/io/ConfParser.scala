package progfun.io

import progfun.core.Land

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

}
