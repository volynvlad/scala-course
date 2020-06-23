import scala.sys
import java.io.File

import scala.collection.immutable.VectorBuilder

object Main extends App {

    val path = "/home/vlad/books/ML"
    // val command = "ls -l"
    // val a = Vector(command, path)
    // sys.process.Process(a.mkString(" ")).!

    // val builderVector = new VectorBuilder[String]

    // val booksNames = builderVector.result

    def getListOfFiles(dir: String, condition: (File => Boolean)): Seq[(Int, String)] = {
        var k = 0
        for {
            file <- new File(dir).listFiles().filter(condition);
            k = k + 1
        } yield (k, file.getName().toString().toLowerCase())
    }

    def getFilter(keyWord: String, filterType: String): (File => Boolean) = {
        (keyWord, filterType) match {
            case (keyWord, "") => 
                println("no filterType")
                file => true
            case (keyWord, "startsWith") => {
                println(s"startsWith $keyWord")
                file => file.toString().toLowerCase().startsWith(keyWord)}
            case (keyWord, "endsWith") => {
                println(s"endsWith $keyWord")
                file => file.toString().toLowerCase().endsWith(keyWord)}
            case (keyWord, "contains") => {
                println(s"contains $keyWord") 
                file => file.toString().toLowerCase().contains(keyWord)}
            case (_, _) => {
                println("there is no keyWord and filterType")
                (_ => true)
            }
        }
    }

    val filterType: String = "contains"
    val keyWord: String = "intoduction"

    val booksNames = getListOfFiles(path, getFilter(keyWord, filterType))
    
    // println(s"files in $path")
    // var k = 0
    // for (bookName <- booksNames) {
    //     println(s"$k: $bookName") 
    //     k += 1
    // }

    // for (bookName <- booksNames) {
    //     // println(s"xdg-open ${List(path, bookName).mkString("/")}")
    //     sys.process.Process(s"xdg-open ${List(path, bookName).mkString("/")}").!
    // }

    println(booksNames)
}