package View

import Controller.MyController
import Models.Song
import tornadofx.*

class SongPicker : Fragment("Song Picker") {
    val controller: MyController by inject()
    val songliste = controller.sl?.list?.asObservable()
    override val root = tableview(songliste) {
        readonlyColumn("Name", Song::_songName)
        readonlyColumn("SongSubName", Song::_songSubName)
        readonlyColumn("Author", Song::_levelAuthorName)
        readonlyColumn("BPM",Song::_beatsPerMinute)

    }
}
