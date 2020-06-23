package by.vlad

object Main {

    trait SimpleTreeSet {
        def +(v: Int): SimpleTreeSet
        def contains(v: Int): Boolean
        def foreach(f: Int => Unit): Unit
    }



}
