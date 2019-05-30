package View

import javafx.scene.paint.Color
import Controller.MyController
import javafx.stage.StageStyle
import tornadofx.*

class MainView: View(){
    override val root = vbox(spacing = 5)
    val controller: MyController by inject()

    init {
        with(root){
            setPrefSize(400.0, 500.0)

            vbox {
                hbox(spacing=20) {
                    text("Path:")
                    val pathText = text(controller.path){
                        fill = Color.DARKORCHID
                    }
                    button("change"){
                        action {
                            pathText.text = controller.getDir()
                        }
                        hboxConstraints { }
                    }
                }
                hbox {
                    spacing = 20.0
                    text("current Song:")
                    val songText = text(controller.getCS()){
                        fill = Color.DARKORCHID
                    }
                    button("change"){
                        action {
                            find<SongPicker>().openModal(stageStyle = StageStyle.UTILITY)
                        }
                    }
                }
                tabpane {

//                    tab<Screen1>()
//                    tab<Screen2>()
//                    tab<Screen3>()
                }
            }

        }
    }
}