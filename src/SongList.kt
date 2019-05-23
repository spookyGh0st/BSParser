import java.io.File
import java.io.IOException
import java.io.Reader
import java.nio.file.Path
import java.nio.file.Paths

class SongList(val path: Path){
    val list = arrayListOf<Song>()


    fun listSongs(){
        list.forEach { println(it.path) }
    }

    //looks in the path directory for every songs
    fun refreshSong() {
        list.clear()
        try {
            path.toFile().walk().maxDepth(1).forEach {
                if (it.isDirectory &&  Paths.get(it.toString(),"info.json").toFile().exists()){
                    addSong(it.toPath())
                }
            }
        } catch (e: IOException) {
            killmeplease(e.message)
        }

    }
    private fun addSong(p: Path){
        println(p.toString())
        val yml:String = Paths.get(p.toString(), "info.json").toFile().readText()

    }
}
