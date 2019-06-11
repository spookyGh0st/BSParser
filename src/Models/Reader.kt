package Models

import com.google.gson.Gson
import java.io.*
import java.lang.Exception
import java.nio.file.Paths

object Reader {
    private val settingsPath = Paths.get(System.getProperty("user.dir"),"BSParser.json")
    private val json:String = settingsPath.toFile().readText()
    val settings = setSettings()

    //tries to return a settings file via the json variable, if it fails or creates an exeption ressets the file
    fun setSettings(): Settings {
        return try {
            var s: Settings? = Gson().fromJson(json, Settings::class.java)
            if (s == null) {
                s = Settings("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Beat Saber\\WIP Songs", "")
            }

            s
        }catch (e:Exception){
            println("error reading setting file, resseting")
            Settings("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Beat Saber\\WIP Songs", "")
        }
    }
    fun writeSettings(){
        try {
            settings.currentSong=CurrentSong.getCSAsString()
            settings.path=WIPPath.getPathAsString()
            val writer = FileWriter(settingsPath.toFile())
            val text = Gson().toJson(settings)
            println(text)
            writer.write(text)
            writer.close()
        }catch (e:Exception){
            killmeplease(e.message)
        }
    }


    fun readPathfromFile(): java.nio.file.Path?{
        var path: java.nio.file.Path? = null
        try {
            if (Paths.get(Reader.settings.path).toFile().isDirectory)
                path=  Paths.get(Reader.settings.path)
        }catch (e: IOException){
            killmeplease(e.message)
        }
        finally {
            return path
        }
    }


    fun readDifficulty(p:java.nio.file.Path?): ArrayList<Difficulty?> {
        val a = arrayListOf<Difficulty?>()

        for(i in 0 until DiffEnum.values().size){
            val file = File (p.toString(),DiffEnum.values()[i].difString + ".dat")
            if (file.exists()){
                val d:Difficulty= createDifficulty(file)
                d.path=file.toPath()
                d.difficulty=DiffEnum.values()[i]
                a.add(d)
            }else{
                a.add(null)
            }
        }
        return a
    }

    private fun createDifficulty(file: File):Difficulty{
        val reader = BufferedReader(FileReader(file))
        val difficultyJson = reader.readText()
        reader.close()
        return Gson().fromJson(difficultyJson, Difficulty::class.java)
    }

    fun writeDifficulty(d: Difficulty){
        try{
            val text = Gson().toJson(d)
            val writer = BufferedWriter(FileWriter(d.path.toFile()))
            writer.write(text)
            writer.close()
        }catch (e:Exception){
            killmeplease(e.message)
        }

    }


}