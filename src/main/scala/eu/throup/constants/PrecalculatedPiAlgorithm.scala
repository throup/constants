package eu.throup
package constants

import java.math.MathContext
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object PrecalculatedPiAlgorithm extends PiAlgorithm {
  override def apply(implicit mc: MathContext): BigDecimal = BigDecimal(precalculated.Pi.value, mc)
  override def apply(p: Int): BigDecimal = this(new MathContext(p))
  override def future(implicit mc: MathContext): Future[BigDecimal] = Future(this(mc))
  override def future(p: Int): Future[BigDecimal] = Future(this(p))
}
