package View

import Models.CurrentSong
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.text.Text
import tornadofx.*
import Controller.MyController


class VaporFrame : View("Vapor Frame") {
    var cs = CurrentSong.getCS()
    var difficulties = FXCollections.observableArrayList<String>()
    var normalLightsDifficulty = SimpleStringProperty()
    var highwayLightsDifficulty = SimpleStringProperty()
    var finalLightsDifficulty = SimpleStringProperty()
    var csProperty = SimpleStringProperty(cs?._songName)
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
                parse()
            }

        }
    }
    fun refresh(){
        cs = CurrentSong.getCS()
        difficulties.clear()
        cs?.songsDifficulties?.forEach {
            difficulties.add(it.difficulty.difString)
        }
        label.text = cs?._songName ?: ""
    }
    init{
        refresh()
    }
    fun parse(){
        controller.parseVaporFrame(cs,normalLightsDifficulty.value,highwayLightsDifficulty.value,finalLightsDifficulty.value)
        close()
    }
}
