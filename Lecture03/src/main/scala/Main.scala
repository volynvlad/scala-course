import java.time.Instant
import java.time.Duration

import scala.math.BigInt
import scala.util.Random

import cats.Eval

object Main extends App {
    val m = Map("one" -> "first", "two" -> "second", "three" -> "third")
    
    println(s"get one = ${m.get("one")}")
    println(s"get fifth = ${m.get("fifth")}")

    val m1 = m + ("five" -> "fifth")
    val m2 = m - "one"

    println(s"m1 = $m1")
    println(s"m2 = $m2")

    // println(m.map(p => p._1.toUpperCase => p._2))
    println(List("one", "two", "three").collect(m2))
    println(m1.get("one"))
    println(m2.get("zero"))

    lazy val lazyCurrent = Instant.now
    val current = Instant.now

    Thread.sleep(30)
    
    println(Duration.between(lazyCurrent, current))

    def repeat(n: Int, v: => Int) {
        lazy val cached = v
        List.fill(n)(cached)
    }

    // final case class UserId(id: String) {
    //     private lazy val loId: String = id.toLowerCase()

    //     override def equals(obj: Any) = {
    //         obj match {
    //             case other: UserId => other.loId == loId
    //             case _ => false
    //         }
    //     }
    // }

    // override def hashCode(): Int = loId.hashCode

    val s: LazyList[Int] = 3 #:: 2 #:: 1 #:: LazyList.empty

    var n: Int = 0
    val s1: LazyList[Int] = LazyList.fill(100000) {
        n += 1
        Random.nextInt
    }

    println(n)

    s1.map(_ * 2).take(1).toVector
    s1.map(_ * 2).take(3).toVector

    println(n)

    def map(s: Stream[Int], f: Int => Int): Stream[Int] = {
        if (s.isEmpty) {
            s
        } else {
            f(s.head) #:: map(s.tail, f)
        }
    }

    val s2: Stream[Int] = (1 to 10_000).toStream

    println(map(s2, _ * 3))

    val fibs: LazyList[BigInt] = 
        BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map {n => n._1 + n._2}

    println(fibs.take(10).toVector.toString)
    
    // монада - значение, помещенное в контекст

    // def findUserId(name: String): Option[Int] = ???
    // def loadUserById(id: Int): Option[User] = ???

    // val opt = Option("username")

    // opt.flatMap(findUserId).flatMap(loadUserById)

    // монада - абстракция цепочек связных вычислений

    // законы, которые должны выполнять монады

    // для любой монадической функции f
    // pure(x).flatMap(f) == f(x)
    // 
    // m.flatMap(pure) == m
    // ассоциативность
    // m.flatMap(f).flatMap(g) == m.flatMap(x => f(x).flatMap(g))

    def loadUserById(id: Int): User = ???
    case class User(id: Int, info: String)
    val result = for {
        v <- Eval.now(10)
        user <- Eval.later(loadUserById(v))
    } yield {
        user.info
    }

    result.value

    def merge(seq1: List[Int], seq2: List[Int]): Eval[List[Int]] = {
        (seq1, seq2) match {
            case (Nil, _) => Eval.now(seq2)
            case (_, Nil) => Eval.now(seq1)
            case (x::xs, y::ys) =>
                if (x < y) {
                    Eval.Unit >> merge(xs, seq2).map(x +: _)
                } else {
                    Eval.Unit >> merge(seq1, ys).map(y +: _)
                }
        }
    }

    println(mergeList.fill(1000)(0), List.fill(1000)(1)).value)

}
