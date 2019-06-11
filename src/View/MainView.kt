package View


import Controller.MyController
import javafx.scene.Parent
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
                    val pathText = text(controller.path){}
                    button("change"){
                        action {
                            pathText.text =controller.getDir()
                        }
                        hboxConstraints { }
                    }
                }
                hbox {
                    spacing = 20.0
                    text("current Song:")
                    val songText = text(controller.getCS())
                    button("change"){
                        action {
                            find<SongPicker>().openModal(block = true,stageStyle = StageStyle.UTILITY)
                            songText.text = controller.getCS()
                        }
                    }
                }
                tabpane {

                    tab<VaporFrame>()
                    tab<ConcertCreator>()
                    tab<DownMapper>()
//                    tab<Screen3>()
                }
            }

        }
    }
}