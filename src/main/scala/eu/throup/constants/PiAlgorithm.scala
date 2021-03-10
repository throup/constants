package eu.throup
package constants

import java.math.MathContext
import scala.concurrent.Future

trait PiAlgorithm {
  def apply(implicit mc: MathContext): BigDecimal
  def apply(p: Int): BigDecimal
  def future(implicit mc: MathContext): Future[BigDecimal]
  def future(p: Int): Future[BigDecimal]
}

