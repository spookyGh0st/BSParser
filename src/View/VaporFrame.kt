package View

import Models.CurrentSong
import Models.Song
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.text.Text
import tornadofx.*

class VaporFrame : View("Vapor Frame") {
    var cs = CurrentSong.getCS()
    var difficulties = FXCollections.observableArrayList<String>()
    var normalLightsDifficulty = SimpleStringProperty()
    var highwayLightsDifficulty = SimpleStringProperty()
    var finalLightsDifficulty = SimpleStringProperty()
    var csProperty = SimpleStringProperty(cs?._songName)
    var label:Text by singleAssign()



    override val root = vbox(spacing = 10.0) {
        hbox(spacing = 10.0) {
            label("loaded Song: ")
            label = text()
            button("refresh"){
                action { refresh() }
            }
        }
        label("Normal Lights")
        combobox(normalLightsDifficulty,difficulties){
            this.selectedItem
        }
        label("Highway Lights")
        combobox(highwayLightsDifficulty,difficulties)
        label("final lights")
        combobox(finalLightsDifficulty,difficulties)
        button("create"){
            action {
                println(normalLightsDifficulty.value)


            }

        }
    }
    fun refresh(){
        cs = CurrentSong.getCS()
        difficulties.clear()
        cs?.songsDifficulties?.forEach {
            difficulties.add(it.difficulty.toString())
        }
        label.text = cs?._songName ?: ""
    }
    init{
        refresh()
    }
}
