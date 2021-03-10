package eu.throup
package constants
package precalculated

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._

import java.math.MathContext
import scala.math.BigDecimal.RoundingMode

class PrecalculatedSpec extends AnyFreeSpec {
  val ref = BigDecimal(Pi.value)
  val algorithm = PrecalculatedPiAlgorithm

  "Pi" - {
    "can be accessed symbolically" in {
      precalculated.π should be(Pi)
    }

    "should have at least 256 digit precision" in {
      ref.precision should be >= 256
    }

    "Chudnovsky precision" - {
      for (i <- 1 to 256) {
        s"Calc with precision $i" in {
          //implicit val mc: MathContext = new MathContext(2)

          val calc = algorithm(i)
          val reference = ref.setScale(i - 1, RoundingMode.HALF_UP)

          calc.precision should be(i)
          calc.toString should be(reference.toString)
        }
      }
    }

    "Chudnovsky MathContext" - {
      "Provided MathContext" - {
        for (i <- 1 to 256) {
          s"Calc with MathContext p=$i" in {
            val mc: MathContext = new MathContext(i) // not implicit

            val calc = algorithm(mc)
            val reference = ref.setScale(i - 1, RoundingMode.HALF_UP)

            calc.precision should be(i)
            calc.toString should be(reference.toString)
            calc.mc should be(mc)
          }
        }
      }

      "Implicit MathContext" - {
        for (i <- 1 to 256) {
          s"Calc with MathContext p=$i" in {
            implicit val mc: MathContext = new MathContext(i)

            val calc = algorithm.apply
            val reference = ref.setScale(i - 1, RoundingMode.HALF_UP)

            calc.precision should be(i)
            calc.toString should be(reference.toString)
            calc.mc should be(mc)
          }
        }
      }

      "No provided MathContext - defaults to package default" in {
        val calc = algorithm.apply
        calc.mc should be(eu.throup.constants.mc)
      }
    }
  }
}
