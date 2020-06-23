package by.vlad

import scala.collection.mutable.ListBuffer

object Main extends App {
    val l: List[Int] = 42 :: 43 :: 44 :: Nil
    // == List(42, 43, 44)

    // this two lists share tail
    // don't construct full copy's
    // they reuse elements, because they are immutable
    val list2 = 10 :: l
    val list3 = 20 :: l

    // this operations are "free"
    println(s"head = ${list2.head}, tail = ${list2.tail}")

    l match {
        case head :: tail =>
            println(head)
            println(tail)
        case Nil =>
            println("empty")
    }

    println(l match {
        case Seq(one) =>
            s"exactly $one"
        case head +: 10 +: tail =>
            (head +: tail).mkString
        case one +: _ =>
            s"at least one element $one"
    })

    // GC take the entire list in iteration process
    def sum(list: List[Int]): Int = {
        list match {
            case Nil =>
                0
            case head :: tail =>
                head + sum(tail)
        }
    }

    @scala.annotation.tailrec // compile in a cycle or throw an error
    def drop(list: List[Int], n: Int): List[Int] = {
        if (n > 0 && list.nonEmpty) {
            drop(list.tail, n - 1)
        } else {
            list
        }
    }

    def dropLoop(list: List[Int], n: Int): List[Int] = {
        var these = list
        var count = n
        while (these.nonEmpty && count > 0) {
            these = these.tail
            count -= 1
        }
        these
    }

    def append(list: List[Int], v: Int): List[Int] = {
        list match {
            case Nil =>
                v :: Nil
            case head :: tail =>
                head :: append(tail, v)
        }
    }

    def append2(list: List[Int], v: Int): List[Int] = {
        (v :: list.reverse).reverse
    }

    def reverse(list: List[Int]): List[Int] = {
        @scala.annotation.tailrec
        def rev(target: List[Int], remains: List[Int]): List[Int] = {
            remains match {
                case head :: tail =>
                    rev(head :: target, tail)
                case Nil =>
                    target
            }
        }
        rev(Nil, list)
    }

    def filter(list: List[Int])(f: Int => Boolean): List[Int] = {
        val builder = ListBuffer[Int]()
        var current = list

        while (current.nonEmpty) {
            if (f(current.head)) {
                builder += current.head
            }
            current = current.tail
        }
        builder.toList
    }

    println(s"list2 = $list2")
    println(s"sum(list2) = ${sum(list2)}")
    println(s"drop(list2, 2) = ${drop(list2, 2)}")
    println(s"dropLoop(list2, 2) = ${dropLoop(list2, 2)}")
    println(s"append(list2, 2) = ${append(list2, 2)}")

    // builder - abstraction for "constructors" with immutable types

    val builder = Vector.newBuilder[Int]
    builder += 1

    println(builder.result())

    val list = List(Some(1), None, Some(3))
    println(list)
    println(list collect {
        case Some(v) => v
    })

    println(for {
        Some(v) <- list
    } yield v)

    val list4: List[Int] = List(1, 1, 1, 2, 3, 3, 4, 5, 5, 5, 6)
    println(list4.foldLeft(List.empty[Int]) { (acc, v) =>
        if (acc.headOption.contains(v)) {
            acc
        } else {
            v :: acc
        }
    }.reverse)
}
