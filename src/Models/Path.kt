package Models

import java.nio.file.Path
import java.nio.file.Paths

object WIPPath {
    private var path: Path? = returnPath()
    fun getPathAsString():String = path.toString()
    fun getPath(): Path? = path


    fun changePath(s:String){
        path = Paths.get(s)
        Reader.writeSettings()
    }

    //tries to get the default steam path, if that fails, returns null
    private fun returnPath(): Path? {
        val songPath = Reader.readPathfromFile()
        if (songPath?.toFile()?.isDirectory == true){
            return songPath
        }
        return null
    }
}