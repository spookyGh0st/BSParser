import netscape.javascript.JSObject
import java.nio.file.Path

class Song(val path: Path){
    val songName:String = ""
    val songSubName:String = ""
    val authorName: String = ""
    val beatsPerMinute: Double = 0.0
    val previewStartTime : Double = 0.0
    val previewDuration: Double = 0.0
    val coverImagePath: String = ""
    val enviromentName: String = ""

    init {
        getInfo()
    }












    fun getInfo(){
        //ZUGRIFFSBESCHRÄNKUNG
        //val json = path.toFile().readText()
        //val jsonObj = JSObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1))


    }
}

