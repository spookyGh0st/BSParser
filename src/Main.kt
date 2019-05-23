import com.google.gson.Gson
import java.io.File
import java.io.FileWriter
import java.nio.file.*
import java.io.IOException
import kotlin.test.todo

fun main() {

    val s = Paths.get(System.getProperty("user.dir").toString(), "BSParser.json")
    val json = s.toFile().readText()
    println(json)
    var ss = Gson().fromJson(json, settings::class.java)
    println(ss.path)

    val p = getPath(ss)
    val songList = SongList(p!!)
    songList.refreshSong()
    songList.listSongs()


    //var path = getStorage()
}


//TODO create object class which handles files

//tries to get the default steam path, if that fails, the user has to manually add it
@Suppress("UNREACHABLE_CODE")
fun getPath(ss: settings): Path? {
    var songpath = readPathfromFile(ss)
    if (songpath == null)
        songpath=readPathfromIO(ss)
    return songpath

}
fun readPathfromFile(ss:settings):Path?{
    try {
        if (Paths.get(ss.path).toFile().isDirectory)
            return Paths.get(ss.path)
        return null
    }catch (e:IOException    ){
        killmeplease(e.message)
        return null
    }
}
fun readPathfromIO(ss: settings):Path?{
    var path: Path? = null
    try {
        println("Please enter the Path to WIP-songs")
        val a = readLine()
        if(File(a).isDirectory){
            ss.path = a.toString()
            //todo  write to file
            path = Paths.get(ss.path)
        }else
            throw IOException("Fehler: konnte ${ss.path} ist kein path")

    }catch (e:IOException){
        killmeplease(e.message)
    }
    finally {
        return path
    }
}





//kills the process and displays the error message
fun killmeplease(a:String?){
    println(a)
    System.exit(-1)
}
