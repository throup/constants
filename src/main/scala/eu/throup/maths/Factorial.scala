package eu.throup
package maths

import scala.annotation.tailrec
import scala.concurrent.{ExecutionContext, Future}

object Factorial {
  def apply(n: BigInt): BigInt = factorial(n, 1)
  def future(n: BigInt)(implicit executor: ExecutionContext): Future[BigInt] = Future(Factorial(n))

  @tailrec
  private def factorial(n: BigInt, a: BigInt): BigInt = if (n == 0) a else factorial(n - 1, n * a)
}
