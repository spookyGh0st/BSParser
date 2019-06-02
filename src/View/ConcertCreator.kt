package View

import tornadofx.*
import Controller.MyController
import Models.CurrentSong
import Models.Difficulty
import Models.Song
import javafx.beans.property.ReadOnlyListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.control.ScrollPane
import javafx.scene.control.SelectionMode
import javafx.scene.control.TableView
import javafx.scene.layout.VBox
import javafx.scene.text.Text

class ConcertCreator : View("Concert Creator"){
    val controller: MyController by inject()
    var cs = CurrentSong.getCS()
    var csName: Text by singleAssign()
    var csDifficulty = SimpleStringProperty()
    var BPMCounter: Text by singleAssign()
    var SelectionVBOX: VBox by singleAssign()

    var songliste = controller.sl?.list?.asObservable()
    var difficulties = FXCollections.observableArrayList<String>()
    var SelectedSongs = FXCollections.observableArrayList<SimpleStringProperty>()


    val allsongs = FXCollections.observableArrayList<String>()

    override val root = vbox(spacing = 10.0) {
        hbox(spacing = 10.0) {
            label("loaded Song: ")
            csName = text()

            button("refresh") {
                action { refresh()  }
            }
        }
        hbox(spacing = 10.0){
            label("selected Difficulty: ")
            combobox(csDifficulty,difficulties){
            }
            text("BPM Changes: ")
            BPMCounter = text()
            button("find"){
                action {

                    println(getDifficulty()?._BPMChanges?.size)
                    BPMCounter.text = getDifficulty()?._BPMChanges?.size.toString()
                    if (BPMCounter.text == "null")
                        BPMCounter.text = "0"
                    createBoxes(BPMCounter.text.toInt())


                }
            }
        }
        scrollpane(true, false) {
            SelectionVBOX = vbox { }
        }
        button("test"){
            action {
                SelectedSongs.forEach { println(it.value.toString()) }
            }
        }
        refresh(true)
    }
    private fun createBoxes(i:Int=0){
        SelectionVBOX.clear()
        if (i == 0)
            return
        SelectedSongs.clear()
        with(SelectionVBOX){
            for (j in 0 until i) {
                hbox(spacing = 20.0) {
                    label("${j+1}.")
                    SelectedSongs.add(SimpleStringProperty(""))
                    combobox(SelectedSongs[j], allsongs){
                        setMaxSize(300.0,50.0)
                    }
                    label("Always uses expert+")
                }

            }
        }

    }


    fun refresh(f: Boolean =false){
        if(cs != CurrentSong.getCS() || f) {
            cs = CurrentSong.getCS()
            difficulties.clear()
            cs?.songsDifficulties?.forEach {
                difficulties.add(it.difficulty.toString())
            }
            csDifficulty.value = null
            csName.text = cs?._songName ?: ""
            BPMCounter.text = ""
            songliste = controller.sl?.list?.asObservable()
            allsongs.clear()
            songliste?.forEach { allsongs.add(it._songName) }
            createBoxes()
        }
    }
    fun getDifficulty(): Difficulty? {
        cs?.songsDifficulties?.forEach {
            if (it.difficulty.toString() == csDifficulty.value)
                return it
        }
        return null
    }
}
