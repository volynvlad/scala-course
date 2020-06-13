package by.vlad

import scala.util.Random

object Main extends App {
    val range: Int = 10_000
    val number: Int = 10
    var vector: Vector[Int] = Vector.fill(number){Random.nextInt(range)}

    def merge(vector: Vector[Int], l: Int, sep: Int, r: Int): Vector[Int] = {
        var retVector: Vector[Int] = Vector.empty

        var leftCounter: Int = l
        var rightCounter: Int = sep + 1

        for (_ <- l to r) {
            println(s"left = $leftCounter, right = $rightCounter")
            println(s"sep = $sep, r = $r")
            if (leftCounter <= sep &&
                (rightCounter > r || vector(leftCounter) <= vector(rightCounter))) {
                retVector :+= vector(leftCounter)
                leftCounter += 1
            } else {
                retVector :+= vector(rightCounter)
                rightCounter += 1
            }
        }
        retVector
    }

    def mergeSort(vector: Vector[Int], l: Int, r: Int) {
        if (l < r) {
            val sep = (l + r) / 2
            mergeSort(vector, l, sep)
            mergeSort(vector, sep + 1, r)
            vector = merge(vector, l, sep, r)
        }
        vector
    }

//    def topNSort(vector: Vector[Int]) {
//
//
//        vector
//    }
//
//    def uniqueMergeSort(vector: Vector[Int]) {
//
//
//        vector
//    }

    println(mergeSort(vector, 0, vector.length))
}
