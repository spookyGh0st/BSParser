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
    val easyDifficulty = createDifficulty(easyJson,difficulty.easy)
    val normalDifficulty = createDifficulty(normalJson,difficulty.normal)
    val hardDifficulty = createDifficulty(hardJson,difficulty.hard)


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
    fun createDifficulty(json:String,difficulty: difficulty):Difficulty {
        val d = Gson().fromJson(json, Difficulty::class.java)
        d.difficulty = difficulty.difString
        d.path = Paths.get(song.path.toString(), "${d.difficulty}.dat")
        return d
    }
    enum class difficulty(val difString: String){
        easy("Easy"),
        normal("Normal"),
        hard("Hard"),
        expert("Expert"),
        expertPlus("ExpertPlus")
    }
}
