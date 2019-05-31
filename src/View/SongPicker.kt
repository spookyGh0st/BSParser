package View

import Controller.MyController
import Models.Song
import tornadofx.*

class SongPicker : Fragment("Song Picker") {
    val controller: MyController by inject()
    val songliste = controller.sl?.list?.asObservable()
    override val root = vbox {
        setPrefSize(350.0,400.0)
        var selectedSong: Song? = null
        tableview(songliste) {
            readonlyColumn("Name", Song::_songName){style{setMaxWidth(200.0)}}
            readonlyColumn("Author", Song::_levelAuthorName){style{setMaxWidth(150.0)}}
            readonlyColumn("BPM", Song::_beatsPerMinute){style{setMaxWidth(50.0)}}
            onSelectionChange { selectedSong = selectedItem }
            onDoubleClick {  controller.pickCS(selectedSong);close()}
        }
        hbox {
            buttonbar {
                button("choose"){
                    action {
                        controller.pickCS(selectedSong)
                        close()
                    }
                }
                button("cancel"){
                    isCancelButton = true
                    action { close() }
                }
            }
        }
    }
}
