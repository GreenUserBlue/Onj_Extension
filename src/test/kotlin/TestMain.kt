import onj.*
import java.io.File

fun main() {
    //TODO: proper tests
    //TODO: boolean calculations

//    OnjJsonPreprocessor().preprocess(File("src/test/res/onj"), File("src/test/res/json"))
    println(OnjParser.parseFile("src/test/res/Test.onj"))

}
