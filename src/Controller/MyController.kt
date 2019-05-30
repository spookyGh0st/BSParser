package Controller
import Models.*

import tornadofx.Controller
import tornadofx.chooseDirectory

class MyController:Controller() {
    var path:String?
    var sl:SongList?
    fun getDir():String {
        path = chooseDirectory(title = "WIP-Path")?.toString() ?: path
        WIPPath.changePath(path!!)
        return path!!
    }

    fun getCS(): String? {
        return sl?.returnCurrentSong()?._songName
    }


    init {
        path = WIPPath.getPathAsString()
        sl = WIPPath.getPath()?.let { SongList(it) }
    }


}