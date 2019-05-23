import com.google.gson.Gson
import java.io.IOException
import java.nio.file.Path
import java.nio.file.Paths



class SongList(val path: Path){
    val list = arrayListOf<Song>()


    fun listSongs(){
        list.forEach { println(it._difficultyBeatmapSets[0]._difficultyBeatmaps[0]._difficulty) }
    }

    //looks in the path directory for every songs
    fun refreshSong() {
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
    private fun addSong(p: Path){
        val yml:String = Paths.get(p.toString(), "info.dat").toFile().readText()
        val s1 = Gson().fromJson(yml, Song::class.java)
        //s1.path = p
        list.add(s1)
    }
}
