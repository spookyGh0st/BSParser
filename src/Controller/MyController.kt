package Controller
import Models.*
import javafx.beans.property.SimpleStringProperty

import tornadofx.Controller
import tornadofx.chooseDirectory

class MyController:Controller() {
    var path:String?
    var sl:SongList?

    fun getDir():String {
        path = chooseDirectory(title = "WIP-Path")?.toString() ?: path
        WIPPath.changePath(path!!)
        sl = SongList(WIPPath.getPath()!!)
        return path!!
    }

    fun getCS(): String? {
        return sl?.returnCurrentSong()?._songName
    }

    fun pickCS(song:Song?){
        CurrentSong.setCurrentSong(song)
        mapdown()
    }

    fun parseVaporFrame(cs: Song?, nLD: String?, hLD: String?, fLD: String?) {
        var normalLightsDifficulty: Difficulty? = null
        var highwayLightsDifficulty: Difficulty? = null
        var finalLightsDifficulty: Difficulty? = null
        if(cs != CurrentSong.getCS())
            println("Fehler, pls refresh")
        else{
            println("success")
            cs?.songsDifficulties?.forEach{
                if (it != null) {
                    when {
                        it.difficulty.difString == nLD -> normalLightsDifficulty = it
                        it.difficulty.difString == hLD -> highwayLightsDifficulty = it
                        it.difficulty.difString == fLD -> finalLightsDifficulty = it
                    }
                }
            }
            parseVaporFrame(normalLightsDifficulty!!, highwayLightsDifficulty!!, finalLightsDifficulty!!)
        }
    }

    fun concertCreator(bBPM: Double?, d:Difficulty, listOfNames: List<SimpleStringProperty>) {

        if (sl == null)return
        val listOfSongs = arrayListOf<Song?>()
        listOfNames.forEach {
            val name = it.value
            if (sl?.containsSong(it.value)!=null)
                listOfSongs.add(sl!!.containsSong(it.value))
        }
        listOfSongs.forEach { println(it?._songName) }
        parseConcertCreator(bBPM, d,listOfSongs)
    }


    init {
        path = WIPPath.getPathAsString()
        sl = WIPPath.getPath()?.let { SongList(it) }
    }


}