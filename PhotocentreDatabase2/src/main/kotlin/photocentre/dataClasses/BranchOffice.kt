package photocentre.dataClasses

import javafx.beans.property.*
import javafx.collections.ObservableList
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
    val branchOffices = SimpleObjectProperty<ObservableList<BranchOffice>>()
    val id = bind(BranchOffice::idProperty)
    val address = bind(BranchOffice::addressProperty)
    val amountOfWorkers = bind(BranchOffice::amountOfWorkersProperty)
}
