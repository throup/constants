package eu.throup

import java.math.{MathContext, RoundingMode}

package object implicits {
  implicit def bigIntToBigDecimal(i: BigInt)(implicit mc: MathContext): BigDecimal = BigDecimal(i, mc)

  implicit class MathContextExt(mc: MathContext) {
    def apply(p: Int): MathContext = new MathContext(p, mc.getRoundingMode)
    def apply(m: RoundingMode): MathContext = new MathContext(mc.getPrecision, m)
    def +(p: Int): MathContext = mc(mc.getPrecision + p)
    def -(p: Int): MathContext = this.+(-p)
  }
}
