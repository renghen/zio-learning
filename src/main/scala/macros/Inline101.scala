package macros

object Inline101:
  inline def increase(value: Double, rate: Double): Double =
    value * (1.0 + rate)

  val value = "2500.0".toDouble
  println(increase(value, 0.02))

  inline def signOf(value: Int): String =
     inline if (value >= 0) "Positive"
     else "Negative"

  @main  
  def runInline() =     
    signOf(42)
