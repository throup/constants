package eu.throup
package constants

import eu.throup.implicits.bigIntToBigDecimal
import eu.throup.implicits.MathContextExt
import eu.throup.maths.Factorial.{future => factorial}
import eu.throup.maths.Power.{future => power}

import java.math.{MathContext, RoundingMode}
import scala.concurrent.Await.result
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.Duration

object ChudnovskyPiAlgorithm extends PiAlgorithm {
  def future(implicit mc: MathContext = implicitly): Future[BigDecimal] = calculatePi(mc + 3).map(_ (mc))
  def future(p: Int): Future[BigDecimal] = future(new MathContext(p, RoundingMode.HALF_UP))
  def apply(implicit mc: MathContext = implicitly): BigDecimal = result(future(mc), Duration.Inf)
  def apply(p: Int): BigDecimal = result(future(p), Duration.Inf)

  private def calculatePi(implicit mc: MathContext): Future[BigDecimal] =
    for (p <- calculateSeries) yield BigDecimal(1, mc) / p

  private def calculateSeries(implicit mc: MathContext): Future[BigDecimal] =
    Future.sequence((0 to steps).map(calculateTerm))
      .map(_.sum)

  private def steps(implicit mc: MathContext): Int = (mc.getPrecision / 14).floor.toInt

  private def calculateTerm(k: Int)(implicit mc: MathContext): Future[BigDecimal] = {
    val n1 = if (k % 2 == 0) 1 else -1
    val n3 = BigInt(545140134) * k + BigInt(13591409)

    val numerator =
      for (n2 <- factorial(6 * k))
        yield n1 * n2 * n3

    val denominator = for {
      d1 <- factorial(3 * k)
      d2 <- factorial(k).map(_.pow(3))
      d3 <- power(640320, 1.5 + 3 * k)
    } yield d1 * d2 * d3

    for {
      n <- numerator
      d <- denominator
    } yield 12 * n / d
  }
}
