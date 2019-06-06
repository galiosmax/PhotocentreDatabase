package photocentre.dataClasses

import javafx.beans.property.*
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.concurrent.Task
import photocentre.controller.BranchOfficeController
import photocentre.controller.Executor
import tornadofx.*

class BranchOffice(id: Long = -1, address: String = "", amountOfWorkers: Int = 0) {

    var id: Long by property(id)
    fun idProperty() = getProperty(BranchOffice::id)

    var address: String by property(address)
    fun addressProperty() = getProperty(BranchOffice::address)

    var amountOfWorkers: Int by property(amountOfWorkers)
    fun amountOfWorkersProperty() = getProperty(BranchOffice::amountOfWorkers)
}

class BranchOfficeModel : ItemViewModel<BranchOffice>() {
    val taskStatus = TaskStatus()
    val branchOfficeController : BranchOfficeController by inject()
    val branchOffices = SimpleObjectProperty<ObservableList<BranchOffice>>()
    val id = bind(BranchOffice::idProperty)
    //val id = bind { item?.idProperty() }
    val address = bind(BranchOffice::addressProperty)
    val amountOfWorkers = bind(BranchOffice::amountOfWorkersProperty)
    //val selectedAddress = bind{ item?.addressProperty()}
//    fun refresh() {
//        runAsync(taskStatus) {
//            updateMessage("Loading offices")
//            updateProgress(0.4, 1.0)
//            branchOfficeController.getBranchOffices()
//        } ui {
//            branchOffices.set(branchOfficeController.offices.asObservable())
//        }
//
//    }
}

//class BranchOfficeScope : Scope() {
//    val BranchOfficeController = photocentre.controller.BranchOfficeController(Executor(p))
//}

//class BranchOfficeModel: ItemViewModel<BranchOffice>() {
//    val id = bind(BranchOffice::id) as LongProperty
//    val address = bind(BranchOffice::address)
//    val amountOfWorkers = bind(BranchOffice::amountOfWorkers) as IntegerProperty
//}