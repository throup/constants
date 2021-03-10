package eu.throup
package constants

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._

class packageSpec extends AnyFreeSpec {
  "Prove the test suite works" - {
    "just some maths" in {
      (1 + 2) should be (3)
    }

    "call a function" in {
      add(1, 2) should be (3)
    }
  }
}
