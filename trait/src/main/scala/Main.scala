import java.nio.channels.FileChannel.MapMode
object Main extends App {
    def code(): Unit = {
        class Animal

        trait Pet {
            def allowedToSleepInBed: Boolean
            def disallowedToSleepInBed: Boolean =
                !allowedToSleepInBed
        }

        class Cat extends Animal with Pet {
            val allowedToSleepInBed: Boolean = true
        }
        class Turtle extends Animal with Pet {
            def allowedToSleepInBed: Boolean = false
        }

        def show(pet: Pet): Unit = {
            println(pet.disallowedToSleepInBed)
        }

        // show(new Cat)
        // show(new Turtle)

        val path = "/home/vlad/progs/scala/trait/src/main/scala/Main.scala"

        val file1 = new java.io.File(path)

        println(file1.getName)

        println("---------")

        trait Timestamp {
            val creationTime: String = {
                val formatter = java.time.format
                                    .DateTimeFormatter
                                    .ofPattern("HH:mm:ss")
                java.time.LocalDateTime.now.format(formatter)
            }
        }

        class FileWithTimestamp(path: String) extends java.io.File(path) with Timestamp

        // val file = new FileWithTimestamp(path)
        type TooLazyToType = java.io.File with Timestamp
        val file: TooLazyToType = new java.io.File(path) with Timestamp {
            def show(): Unit = {
                println("hi")
            }
        }

        def showName(file: TooLazyToType): Unit = {
            println(file.getName)
        }

        def showCreationTime(timestamp: TooLazyToType): Unit = {
            println(file.creationTime)
        }

        showName(file)
        showCreationTime(file)



    }
    // code()

    final class Lamborgini (override val model: String) 
        extends Core.SportsCar(model) 
        with Modification.Spoiler

    final class BMW(override val model: String)
        extends Core.OrdinaryCar(model)
        with Modification.Spoiler
        with Modification.EngineControlUnit
        with Modification.turboCharger

    
    println(new Lamborgini("first car"))
    println(new BMW("second car"))

    class Person(val name: String) extends AnyRef

    val bobPerson = new Person("bob")

    println(bobPerson.name.toString)
    println(bobPerson.getClass.toString)
    println(bobPerson.toString)
    
    val bob = new AnyRef {
        def name: String = "bob"
    }

    println(bob.name.toString)
    println(bob.getClass.toString)
    println(bob.toString)

}