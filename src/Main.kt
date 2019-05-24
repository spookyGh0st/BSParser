import java.nio.file.Path

fun main() {
    println("Using WIP-Folder: "+Reader.path)
    val songlist = SongList(Path.of(Reader.settings.path))
    val parser = Parser(songlist.returnCurrentSong())
    println(parser.easyDifficulty.path)
    println(parser.easyDifficulty.difficulty)
}








//kills the process and displays the error message
fun killmeplease(a:String?){
    println(a)
    System.exit(-1)
}
