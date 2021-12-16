package progfun.io

object ConfLoader {

  // Get lines from a file
  def load(fileName: String): List[String] = {
    val source = scala.io.Source.fromFile(fileName)
    val lines = source.getLines().toList
    source.close
    lines
  }

}
