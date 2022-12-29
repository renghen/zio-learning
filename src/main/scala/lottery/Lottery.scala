package lottery

import zio._
import zio.Console._

object LotteryApp extends ZIOAppDefault {

  def generateNumber(): UIO[Int] =
    for {
      number <- Random.nextIntBetween(1, 41)
    } yield number

  def lotteryGenerator() =
    ZIO.foreach(1 to 10)(_ => generateNumber())

  def run =
    printLine("lotto generated numbers ...") *> ZIO.foreach(1 to 10)(i =>
      lotteryLogic(i)
    )

  def lotteryLogic(index : Int) =
    for {
      _ <- ZIO.foreachPar(1 to 100)(_ => lotteryGenerator())
      numbers <- lotteryGenerator()
      // numbers <- ZIO.iterate(numbers){num => !num.contains(40)}{ num => lotteryGenerator() }
      toDisplay = numbers.distinct.sorted        
        .map(i => f"$i%02d")
        .take(6)
        .mkString(", ")
      indexFmt = f"$index%02d"  
      _ <- printLine(s"$indexFmt: ${toDisplay}")
    } yield ()
}
