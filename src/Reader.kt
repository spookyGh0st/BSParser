import com.google.gson.Gson
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.lang.Exception
import java.nio.file.Path
import java.nio.file.Paths

object Reader {
    private val settingsPath = Paths.get(System.getProperty("user.dir"),"BSParser.json")
    private val json:String = settingsPath.toFile().readText()
    val settings = setSettings()
    val path = returnPath()

    //tries to return a settings file via the json variable, if it fails or creates an exeption ressets the file
    private fun setSettings(): Settings{
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


    //tries to get the default steam path, if that fails, the user has to manually add it
    @Suppress("UNREACHABLE_CODE")
    private fun returnPath(): Path? {
        var songpath = readPathfromFile()
        if (songpath == null)
            songpath=readPathfromIO()
        return songpath
    }

    private fun readPathfromFile(): Path?{
        var path: Path? = null
        try {
            if (Paths.get(settings.path).toFile().isDirectory)
                path=  Paths.get(settings.path)
        }catch (e: IOException){
            killmeplease(e.message)
        }
        finally {
            return path
        }
    }
    @Suppress("UNREACHABLE_CODE")
    private fun readPathfromIO(): Path?{
        var path: Path? = null
        try {
            println("Please enter the Path to WIP-songs")
            val a = readLine()
            if(File(a).isDirectory){
                path = File(a).toPath()
                settings.path = a.toString()
                writeSettings()
            }else{      throw IOException("Error: $a is not a path")    }

        }catch (e: IOException){
            killmeplease(e.message)
        }
        finally {
            return path
        }
    }

    //TODO make private
    fun writeSettings(){
        val writer = FileWriter(settingsPath.toFile())
        val text = Gson().toJson(settings)
        writer.write(text)
        writer.close()

    }
}