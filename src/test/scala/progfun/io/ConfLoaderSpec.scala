package progfun.io

import org.scalatest.funsuite.AnyFunSuite

import java.io.FileNotFoundException

class ConfLoaderSpec extends AnyFunSuite {

  test("ConfLoader.load") {
    val conf = ConfLoader.load("src/test/resources/conf_test.txt")
    assert(conf(0) == "5 5")
    assert(conf(1) == "1 2 N")
    assert(conf(2) == "GAGAGAGAA")
    assert(conf(3) == "3 3 E")
    assert(conf(4) == "AADAADADDA")
  }

  test("ConfLoader.load fail") {
    assertThrows[FileNotFoundException]{
      ConfLoader.load("src/test/resources/conf_test_fail.txt")
    }
  }

}
