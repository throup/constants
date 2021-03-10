package eu.throup
package maths

import ch.obermuhlner.math.big.BigDecimalMath

import java.math.MathContext
import scala.concurrent.{ExecutionContext, Future}

object Power {
  def apply(x: BigDecimal, y: BigDecimal)(implicit mc: MathContext): BigDecimal = BigDecimalMath.pow(x, y, mc)
  def future(x: BigDecimal, y: BigDecimal)(implicit mc: MathContext, executor: ExecutionContext): Future[BigDecimal] = Future(Power(x, y))

  implicit private def scala2java(s: BigDecimal): java.math.BigDecimal = s.bigDecimal
}
