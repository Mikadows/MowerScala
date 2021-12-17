package progfun.io

object ConfLoader {

  /**
   * Loads a configuration from a file.
   * @param fileName the path of the file to load
   * @return a list of configuration lines
   */
  def load(fileName: String): List[String] = {
    val source = scala.io.Source.fromFile(fileName)
    val lines = source.getLines().toList
    source.close
    lines
  }

}
