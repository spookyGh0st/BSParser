import java.nio.file.Path

fun main() {
    println("Using WIP-Folder: "+Reader.path)
    val songlist = SongList(Path.of(Reader.settings.path))
    println(songlist.returnCurrentSong()._songName)


    //val songList = null
    //songList.refreshSong()
    //songList.listSongs()


    //var path = getStorage()
}


//TODO create object class which handles files






//kills the process and displays the error message
fun killmeplease(a:String?){
    println(a)
    System.exit(-1)
}
