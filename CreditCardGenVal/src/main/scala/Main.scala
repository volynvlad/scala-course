object Main {
    def main(args: Array[String]): Unit = {
        println("-" * 50)

        code(args)

        println("-" * 50)
    }

    def code(args: Array[String]): Unit = {
        println("Hello")

        CreditCard.Valid("hey")
        new CreditCard.Valid("hey")
    }
}