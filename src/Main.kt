import java.nio.file.*
import java.io.IOException

fun main() {
    val p = getPath()
    val songlist = SongList(p)
    songlist.refreshSong()
    songlist.listSongs()


    //var path = getStorage()


}

fun getPath():Path{
    var songPath = Paths.get(System.getenv("ProgramFiles(X86)"), "Steam", "steamapps", "common", "Beat Saber", "WIP Songs")
    try {
        if (!Files.isDirectory(songPath)) {
            println("\nCould not find the Path to the Wip Folder")
            println("please enter it (e.g. C:\\Program Files (x86)\\Steam\\steamapps\\common\\Beat Saber\\WIP Songs")
            print("path: ")
            songPath = Paths.get(readLine())
        }
        if(!Files.isDirectory(songPath)){
            throw IOException("$songPath is not an directory, exiting")
        }
    }catch (e:IOException){
        killmeplease(e.message)
    }finally {
        return songPath
    }
}




fun killmeplease(a:String?){
    println(a)
    System.exit(-1)
}
