package types

trait Resettable:
  def reset(): Unit

trait Growable[T]:
  def add(t: T): Unit
  def show(): Unit

object Union101:
  val someUnion = new Resettable with Growable[String]:
    val buffer = collection.mutable.Buffer.empty[String]
    def reset() =
      buffer.clear()

    def add(t: String) = buffer.append(t)

    def show() = println(buffer.mkString(","))


  def f(x: Resettable & Growable[String]) =
    x.reset()
    x.add("first")
  end f

@main
def mainUnion =
  import Union101._
  f(someUnion)
  someUnion.show()
