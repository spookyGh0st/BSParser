import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.nio.file.Paths

class Parser(val song:Song) {
    var easyJson = readDifficulty("Easy.dat")
    var normalJson = readDifficulty("Normal.dat")
    var hardJson = readDifficulty("Hard.dat")
    val easyDifficulty = createDifficulty(easyJson,DiffEnum.easy)
    val normalDifficulty = createDifficulty(normalJson,DiffEnum.normal)
    val hardDifficulty = createDifficulty(hardJson,DiffEnum.hard)


    private fun readDifficulty(difficulty: String):String{
        return try{
            val file = File(song.path.toString(), difficulty)
            val reader =BufferedReader(FileReader(file))
            val ergebniss = reader.readLine()
            reader.close()
            ergebniss
        }catch (e:IOException) {
            killmeplease(e.message)
            ""
        }
    }
    private fun createDifficulty(json:String, DiffEnum: DiffEnum):Difficulty {
        val d = Gson().fromJson(json, Difficulty::class.java)
        d.difficulty = DiffEnum
        d.path = Paths.get(song.path.toString(), "${d.difficulty}.dat")
        return d
    }
}
