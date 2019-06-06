package photocentre.view

import javafx.scene.Scene
import photocentre.controller.BranchOfficeController
import photocentre.controller.Db
import photocentre.controller.Executor
import photocentre.controller.PhotocentreDataSource
import photocentre.dao.BranchOfficeDao
import tornadofx.*

class DatabaseApp : App(Workspace::class) {

    //val personModel : PersonModel by inject()
    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 1280.0, 768.0)

    override fun onBeforeShow(view: UIComponent) {
        with (workspace) {
            dock<MainView>()
        }
    }

}

fun main(args: Array<String>) {
    launch<DatabaseApp>(args)
}