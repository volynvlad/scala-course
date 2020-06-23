object Main {
    def main(args: Array[String]): Unit = {
        lazy val myFavoriteNumber = {
            println("I'm running!")

            42
        }

        println(s"First time: $myFavoriteNumber")
        println(s"Second time: $myFavoriteNumber")
    }
}
