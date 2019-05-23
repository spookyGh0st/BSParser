import netscape.javascript.JSObject
import java.nio.file.Path

class Song(
    val path: Path,
    var difficultyLevel: ArrayList<Difficulty>,
    val songName: String = "",
    val songSubName: String = "",
    val authorName: String = "",
    val beatsPerMinute: Double = 0.0,
    val previewStartTime: Double = 0.0,
    val previewDuration: Double = 0.0,
    val coverImagePath: String = "",
    val enviromentName: String = "",
    val oneSaber: Boolean = false,
    val customEnvironmentHash: String = ""
){



    inner class Difficulty(
        difficulty: String = "",
        difficultyRank: Int = 0,
        audioPath: String= "",
        jsonPath:String = "",
        offset:Double=0.0,
        oldOffset:Double=0.0,
        ChromaToggle:String=""
    )
}

