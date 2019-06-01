package Controller
import Models.*
import javafx.beans.property.SimpleStringProperty

import tornadofx.Controller
import tornadofx.chooseDirectory

class MyController:Controller() {
    var path:String?
    var sl:SongList?
    var parser = Parser()

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
                when {
                    it.difficulty.toString() == nLD -> normalLightsDifficulty = it
                    it.difficulty.toString() == hLD -> highwayLightsDifficulty = it
                    it.difficulty.toString() == fLD -> finalLightsDifficulty = it
                }
            }
            parser.parseVaporFrame(normalLightsDifficulty!!, highwayLightsDifficulty!!, finalLightsDifficulty!!)
        }
    }


    init {
        path = WIPPath.getPathAsString()
        sl = WIPPath.getPath()?.let { SongList(it) }
    }


}