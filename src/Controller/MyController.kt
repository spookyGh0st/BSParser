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
        sl = WIPPath.getPath().let { SongList(it!!) }
        return path!!
    }

    fun getCS(): String? {
        println(sl?.returnCurrentSong()?._difficultyBeatmapSets?.forEach{it})
        return sl?.returnCurrentSong()?._songName
    }

    fun pickCS(song:Song?){
        CurrentSong.setCurrentSong(song)
    }


    init {
        path = WIPPath.getPathAsString()
        sl = WIPPath.getPath()?.let { SongList(it) }
    }


}