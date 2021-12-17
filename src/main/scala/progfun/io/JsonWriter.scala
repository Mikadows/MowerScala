package progfun.io

import play.api.libs.json.{JsArray, JsObject, Json}
import progfun.core.{Land, Mower}

import scala.annotation.tailrec

/**
 * Writes a list of mowers to a JSON format.
 */
object JsonWriter {

  /**
   * Write the list of mowers to a JSON format.
   * @param land The land.
   * @return
   */
  private def writeLand(land: Land): JsObject = {
    Json.obj(
      "x" -> land.xSize,
      "y" -> land.ySize
    )
  }

  /**
   * Write the list of mowers to a JSON format.
   * @param rawMower The raw mower.
   * @param executedMower The executed mower.
   * @return The JSON object.
   */
  private def mowersToJson(rawMower: List[Mower], executedMower: List[Mower]): JsArray = {
    mowerToJson(rawMower, executedMower, Json.arr())
  }

  @tailrec
  private def mowerToJson(rawMower: List[Mower], executedMower: List[Mower], json_arr: JsArray): JsArray =
      (rawMower, executedMower) match {
        case (head1 :: tail1, head2 :: tail2) =>
          mowerToJson(tail1, tail2, json_arr :+ writeMower(head1, head2))
        case _ => json_arr
      }

  /**
   * Write the mower to a JSON format.
   * @param rawMower The raw mower.
   * @param executedMower The executed mower.
   * @return The JSON object.
   */
  private def writeMower(rawMower: Mower, executedMower: Mower): JsObject = {
    Json.obj(
      "debut" -> Json.obj(
        "point" -> Json.obj(
          "x" -> rawMower.position.x,
          "y" -> rawMower.position.y
        ),
        "direction" -> rawMower.position.direction.toString
      ),
      "instructions" -> rawMower.actions.map(_.toString),
      "fin" -> Json.obj(
        "point" -> Json
          .obj("x" -> executedMower.position.x, "y" -> executedMower.position.y),
        "direction" -> executedMower.position.direction.toString
      )
    )
  }

  /**
   * Write the entire environment to a JSON format.
   * @param land The land.
   * @param rawMowers The raw mowers.
   * @param executedMowers The executed mowers.
   * @return The JSON string.
   */
  def toJson(land: Land, rawMowers: List[Mower], executedMowers: List[Mower]): String = {
    Json.prettyPrint(
      Json.obj(
        "limite" -> writeLand(land),
        "tondeuses" -> mowersToJson(rawMowers, executedMowers)
      )
    )
  }
}
