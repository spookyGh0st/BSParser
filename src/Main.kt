import java.nio.file.Path

fun main() {
    println("Using WIP-Folder: "+Reader.path)
    val songlist = SongList(Path.of(Reader.settings.path))
    val parser = Parser(songlist.returnCurrentSong())
    parser.parse()
}


//kills the process and displays the error message
fun killmeplease(a:String?){
    println(a)
    System.exit(-1)
}
