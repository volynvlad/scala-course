package by.vlad

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.util.{Random, Try}

// inheritance from App
object Main extends App {

    println({
        println("start")
        println(repeatChar("""-""", 30))
        println("functions")
        def linear(x: Int): Int = x

        def square(x: Int): Int = x * x

        def cube(x: Int): Int = x * x * x

        def sum(x: Int): Int = linear(x) + square(x) + cube(x)

        def abs(x: Int): Int = {
            if (x >= 0) {
                x
            } else {
                -x
            }
        }

        def repeatChar(c: String, n: Int): String = {
            @tailrec
            def repeatCharAcc(c: String, n: Int, accum: String): String = {
                if (n == 0) accum
                else repeatCharAcc(c, n - 1, accum + c: String)
            }
            repeatCharAcc(c, n, """""")
        }

        val f: Int => Int = square
        //        another way to write
        //        val f2: Function1[Int, Int] = square
        var str = "Hello "
        val add = "world!"
        str += add

        println(str)
        println(f(10))
        println(abs({5}) + abs(-2))

        var a: Int = 0

        println(repeatChar("""-""", 30))
        println("while for")
        while (sum(a) < 100) {
            println(s"${sum(a)} < 100")
            a += 1
        }
        println(sum(a))

        println("to included")
        for (i <- 0 to 10) {
            print(i + ", ")
        }
        println()

        println("until excluded")
        for (i <- 0 until 10) {
            print(i + ", ")
        }
        println()

        println(repeatChar("""-""", 30))

        println(for (v <- Set(1, 2, 3)) yield {
            v * v
        })

        println(for {
            v <- List(1, 2, 3, 4) if v % 2 == 0
        } yield {
            v * v
        })


        println(repeatChar("""-""", 30))
        println("collections")
        // Tuple to 22
        // Tuple2[Int, Int]
        // construction
        val pair: (Int, Int) = (1, 2)
        // deconstruction

        val (first, second) = pair

        val seq = Seq[Int]() // general type for collections, which have order
        var arrayBuffer = ArrayBuffer[Int]() // analog of ArrayList in java
        val vector = Vector[Int]() // un-mutable analog of ArrayList

        val buffer = ArrayBuffer[Int](0, 1, 2)

        buffer += 4

        println(buffer(1))

        // buffer also a function
        val f1: (Int => Int) = buffer

        val v = Vector(1, 2, 3, 4)

        val (first1, second1) = v.splitAt(v.length / 2)

        println(v)
        println(first1)
        println(second1)

        // Algebraic types

        println(repeatChar("""-""", 30))
        println("pattern matching")

        // with pattern matching
        sealed trait Expr extends Product with Serializable

        case class Number(value: Int) extends Expr {
            override def equals(that: Any): Boolean = value == that
        }
        case class Plus(lhs: Expr, rhs: Expr) extends Expr {
            override def equals(that: Any): Boolean = ???
        }
        case class Minus(lhs: Expr, rhs: Expr) extends Expr {
            override def equals(that: Any): Boolean = ???
        }

        def value(expression: Expr): Int = expression match {
            case Number(value) => value
            case Plus(lhs, rhs) => value(lhs) + value(rhs)
            case Minus(lhs, rhs) => value(lhs) - value(rhs)
        }

        println(s"result = ${value(Plus(Number(2), Number(2)))}")

        // with OOP

        trait Expr1 {
            def eval: Int
        }

        case class Number1(value: Int) extends Expr1 {
            override val eval = value

            override def equals(that: Any): Boolean = ???
        }
        case class Plus1(lhs: Expr1, rhs: Expr1) extends Expr1 {
            override def eval: Int = lhs.eval + rhs.eval

            override def equals(that: Any): Boolean = ???
        }
        case class Minus1(lhs: Expr1, rhs: Expr1) extends Expr1 {
            override def eval: Int = lhs.eval - rhs.eval

            override def equals(that: Any): Boolean = ???
        }

        println(s"result = ${Plus1(Number1(2), Number1(2)).eval}")

        println(repeatChar("""-""", 30))
        println("Option")

        // there are two models
        // 1. fixed schema of data, huge variety of operations --- Pattern Matching
        // 2. fixed operations, huge variety of objects --- OOP

        val v1 = Vector(1, 2, 3, 4, 5)

        val r: Option[Int] = v1.find(x => x > 2)

        // print Option type
        // one way
        r match {
            case Some(k) => println(k)
            case None => println("None")
        }

        // second way
        println(r.getOrElse("None"))

        println(repeatChar("""-""", 30))
        println("Try")

        //        try {
        //            1 / 0
        //        } catch {
        //            case ex: IllegalArgumentException => println("division by zero")
        //        }
        // Try
        // Success - contained the result
        // Failure - contained the exception
        // function can return Try, but this is anti-pattern

        val vector1 = Vector.fill(10) {
            Try {
                1 / Random.nextInt(5)
            }
        }

        println(s"number of success divisions - ${vector1.count(x => x.isSuccess)}")

        // Either[A, B]
        // Right(x)
        // Left(y)
        println(repeatChar("""-""", 30))

        "end"
    })
}

