import com.google.gson.Gson
import java.io.IOException
import java.lang.Exception
import java.nio.file.Path
import java.nio.file.Paths



class SongList(val path: Path){
    val list = arrayListOf<Song>()
    init {
        refreshSong()
    }

    fun returnCurrentSong():Song{
        try {
            var s = Reader.settings.currentSong
            list.forEach {
                if (it._songName == s) return it
            }
            listSongs()
            println("\nFailed to find current song, please enter Song name (case-sensitive): ")
            s = readLine().toString()
            list.forEach {
                if (it._songName == s) {
                    Reader.settings.currentSong=s
                    Reader.writeSettings()
                    return it
                }
            }
            return returnCurrentSong()
        }catch (e:Exception){
            killmeplease(e.message)
            return list[0]
        }
    }

    //who the fucks needs documentation for this
    fun listSongs(){
        println("\nList of all Songs: (only new format)")
        list.forEach { println(it._songName)}
    }

    //looks in the path directory for every songs, or kills it
    private fun refreshSong() {
        list.clear()
        try {
            path.toFile().walk().maxDepth(1).forEach {
                if (it.isDirectory &&  Paths.get(it.toString(),"info.dat").toFile().exists()){
                    addSong(it.toPath())
                }
            }
        } catch (e: IOException) {
            killmeplease(e.message)
        }
    }

    //adds a song under the given Path to the playlist
    private fun addSong(p: Path){
        val yml:String = Paths.get(p.toString(), "info.dat").toFile().readText()
        val s1 = Gson().fromJson(yml, Song::class.java)
        s1.path = p
        list.add(s1)
    }
}
