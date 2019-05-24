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


    fun parse(){
        println("\n\nEditing ${Reader.settings.currentSong}")
        println("\nModifying normal map and merging it with easy map into the hard map")
        println("this cannot be reverted. Continue? (y/n)")
        if (readLine()?.toLowerCase()  != "y")
            killmeplease("Exiting...")
        normalDifficulty._events.forEach {
            when(it._type){
                0 -> it._type= 5
                1 -> it._type = 6
                2 -> it._type = 7
                3 -> it._type = 10
                4 -> it._type = 11
            }
        }
        println("1/3")
        hardDifficulty._events = easyDifficulty._events.plus(normalDifficulty._events)
        println("2/3")
        hardDifficulty._events = hardDifficulty._events.sortedBy { it._time }
        println("3/3")
        //TODO write hardDifficulty

    }


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
