package View

import Models.CurrentSong
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.text.Text
import tornadofx.*
import Controller.MyController
import Models.mapdown
import javafx.beans.property.BooleanProperty
import javafx.beans.property.SimpleBooleanProperty


class ComplexStreamCreator : View("complexStreamCreator") {
    var cs = CurrentSong.getCS()
    var label:Text by singleAssign()
    val controller: MyController by inject()
    val SkyStream = SimpleBooleanProperty(false)



    override val root = vbox(spacing = 10.0) {
        hbox(spacing = 10.0) {
            label("loaded Song: ")
            label = text()
            button("refresh"){
                action { refresh() }
            }
        }
        text("to create complex Stream use bookmarks with /startCS and /endCS")
        checkbox("ScyStream",SkyStream)
        button("create"){
            action {
                controller.mapComplexStreams(SkyStream.value)
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
