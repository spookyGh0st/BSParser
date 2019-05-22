import java.io.IOException
import java.nio.file.Path

class SongList(val path: Path){
    val list = arrayListOf<Song>()


    fun listSongs(){
        list.forEach { println(it.path) }
    }

    fun refreshSong() {
        list.clear()

        try {

            path.toFile().walk().maxDepth(1).forEach {
                list.add(Song(it.toPath()))
            }
        } catch (e: IOException) {
            killmeplease(e.message)
        }

    }
}