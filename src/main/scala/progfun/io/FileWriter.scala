package progfun.io

import java.io._

/**
 * A class that writes content to a file.
 */
object FileWriter {

  def write(fileName: String, content: String) = {
    val file = new File(fileName)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(content)
    bw.close()
  }

}
