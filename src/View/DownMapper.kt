package View

import Models.CurrentSong
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.text.Text
import tornadofx.*
import Controller.MyController
import Models.mapdown


class DownMapper : View("Downmapper") {
    var cs = CurrentSong.getCS()
    var label:Text by singleAssign()
    val controller: MyController by inject()



    override val root = vbox(spacing = 10.0) {
        hbox(spacing = 10.0) {
            label("loaded Song: ")
            label = text()
            button("refresh"){
                action { refresh() }
            }
        }
        button("create"){
            action {
                mapdown()
                close()
            }

        }
    }
    fun refresh(){
        cs = CurrentSong.getCS()
        label.text = cs?._songName ?: ""
    }
    init{
        refresh()
    }
}
