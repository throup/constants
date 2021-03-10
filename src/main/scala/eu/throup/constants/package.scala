package eu.throup

import java.math.MathContext

package object constants {
  implicit def mc: MathContext = {
    new MathContext(256)
  }
}
