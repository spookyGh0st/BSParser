import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class Parser(val song:Song) {
    var easy = readDifficulty("Easy.dat")
    var normal = readDifficulty("Normal.dat")
    var hard = readDifficulty("Hard.dat")


    fun readDifficulty(difficulty: String):String{
        return try{
            val file = File(song.path.toString(), difficulty)
            val reader =BufferedReader(FileReader(file))
            println(file.toString())
            val ergebniss = reader.readLine()
            reader.close()
            ergebniss
        }catch (e:IOException) {
            killmeplease(e.message)
            ""
        }
    }
}
