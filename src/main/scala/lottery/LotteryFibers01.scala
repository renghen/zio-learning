package lottery

import zio._
import zio.Console._

import scala.util.chaining.*

object LotteryFibers01 extends ZIOAppDefault:
  val print: Any => zio.IO[java.io.IOException, Unit] = printLine

  extension [A](input: A) inline def |>[B](f: (A) => B): B = input.pipe(f)

  def run =
    print("lotto generated numbers ...") *> ZIO
      .foreach(1 to 10)(i =>
        lotteryGenerator().flatMap { line =>
          line |> formatNumbers |> print
        }
      )
      .orDie

  def generateNumber(): UIO[Int] =
    for number <- Random.nextIntBetween(1, 41)
    yield number

  def lotteryGenerator() =
    for
      numbers <- ZIO.iterate(Set.empty[Int])(set => set.size != 6) { set =>
        generateNumber().map { i => set + i }
      }
      sortedNumbers = numbers.toList.sorted
    yield sortedNumbers

  def formatNumbers(lst: List[Int]) =
    lst.map(i => f"$i%02d").mkString(", ")
