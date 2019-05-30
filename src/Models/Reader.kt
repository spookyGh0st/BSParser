package Models

import com.google.gson.Gson
import java.io.FileWriter
import java.io.IOException
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



    fun writeDifficulty(d: Difficulty){
        try{
            val writer = FileWriter(d.path.toFile())
            val text = Gson().toJson(d)
            //println(text)
            writer.write(text)
            writer.close()
        }catch (e:Exception){
            killmeplease(e.message)
        }

    }


}