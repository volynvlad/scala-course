package by.vlad
import scala.util.Random

object Main extends App {
    val range: Int = 1000
    val number: Int = 2000
    val n: Int = number / 10
    var list: List[Int] = List.fill(number){Random.nextInt(range)}
    var vector: Vector[Int] = Vector.fill(number){Random.nextInt(range)}

    def mergeSort(vector: Vector[Int], ascend: Boolean): Vector[Int] = {
        val n = vector.length / 2
        if (n == 0) vector
        else {
            def merge(left: Vector[Int], right: Vector[Int]): Vector[Int] = (left, right) match {
                case(IndexedSeq(), right) => right
                case(left, IndexedSeq()) => left
                case(left, right) =>
                    if (!(ascend ^ (left(0) < right(0)))) left(0) +: merge(left.drop(1), right)
                    else right(0) +: merge(left, right.drop(1))
            }
            val (left, right) = vector splitAt(n)
            merge(mergeSort(left, ascend), mergeSort(right, ascend))
        }
    }

    def mergeSort(list: List[Int], ascend: Boolean): List[Int] = {
        val n = list.length / 2
        if (n == 0) list
        else {
            def merge(left: List[Int], right: List[Int]): List[Int] =
                (left, right) match {
                    case (Nil, right) => right
                    case (left, Nil) => left
                    case (topLeft :: left1, topRight :: right1) =>
                        if (!(ascend ^ (topLeft < topRight))) topLeft :: merge(left1, right)
                        else topRight :: merge(left, right1)
                }
            val (left, right) = list splitAt(n)
            merge(mergeSort(left, ascend), mergeSort(right, ascend))
        }
    }

    def topNSort[T](iterable: Iterable[T], n: Int)(implicit ord: Ordering[T]): Iterable[T] = {
        def partitionMax(acc: Iterable[T], it: Iterable[T]): Iterable[T]  = {
            val max = it.max(ord)
            val (nextElems, rest) = it.partition(ord.gteq(_, max))
            val maxElems = acc ++ nextElems
            if (maxElems.size >= n || rest.isEmpty) maxElems.take(n)
            else partitionMax(maxElems, rest)
        }
        if (iterable.isEmpty) iterable.take(0)
        else partitionMax(iterable.take(0), iterable)
    }

    def uniqueMergeSort(vector: Vector[Int], ascend: Boolean): Vector[Int] = {
        val n = vector.length / 2
        if (n == 0) vector
        else {
            def merge(left: Vector[Int], right: Vector[Int]): Vector[Int] = (left, right) match {
                case(IndexedSeq(), right) => right
                case(left, IndexedSeq()) => left
                case(left, right) =>
                    if (left(0) == right(0)) merge(left.drop(1), right)
                    else if (!(ascend ^ (left(0) < right(0)))) left(0) +: merge(left.drop(1), right)
                    else right(0) +: merge(left, right.drop(1))
            }
            val (left, right) = vector splitAt(n)
            merge(uniqueMergeSort(left, ascend), uniqueMergeSort(right, ascend))
        }
    }

    println("mergesort")
    println(mergeSort(list, true))
    println(mergeSort(list, false))
    println(mergeSort(vector, true))
    println(mergeSort(vector, false))

    println("topNSort")
    println(topNSort(vector, n) == vector.sorted.takeRight(n).reverse)
    println(topNSort(vector, n))
    println(vector.sorted.takeRight(n).reverse)

    println("uniqueMergeSort")
    println(uniqueMergeSort(vector, true))
    println(uniqueMergeSort(vector, false))

}
