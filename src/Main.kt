import java.nio.file.*
import java.io.IOException
import kotlin.system.exitProcess

fun main() {

    var p: Path = getPath()
    println(p)

    //var path = getStorage()


}

fun getPath():Path{
    var home: String = System.getProperty("user.home")
    var p = Paths.get(home, "AppData","Roaming","BSParser")
    try {
        if(!Files.isDirectory(p)){
            println("creating AppData Directory")
            Files.createDirectories(p)
        }
    }catch (e: IOException ){
        println("Please enter the Settings folder manually")
        var tempP = readLine()
        p = Paths.get(tempP)
    }finally {
        if(!Files.isDirectory(p)){
            println("failed to initialized Path, exiting")
            exitProcess(-1)
        }
        return p
    }

}
