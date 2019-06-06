package photocentre.view

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.TextField
import photocentre.controller.BranchOfficeController
import photocentre.controller.Executor
import photocentre.controller.PhotocentreDataSource
import photocentre.dao.BranchOfficeDao
import photocentre.dataClasses.BranchOfficeModel
import tornadofx.*

class BranchOfficeSelectCountOfficeFragment(photocentreDataSource: PhotocentreDataSource) : Fragment() {
    val branchOfficeModel: BranchOfficeModel by inject()
    val totalBranchOffice: TotalBranchOfficeModel by inject()
    val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    val branchOfficeController = BranchOfficeController(Executor(photocentreDataSource, branchOfficeDao))
    var amount: Int = 0
    init {
        amount = branchOfficeController.countBranchOffices()
    }

    private var count = FXCollections.observableArrayList<TotalBranchOffice>(
            TotalBranchOffice(amount)
    )
    override val root = vbox {
        tableview(count) {
            column("Total offices", TotalBranchOffice::amountProperty)
            //bindSelected(totalBranchOffice)
        }
        button("Back") {
            action {
                val branchOfficeCRUDFragment = BranchOfficeCRUDFragment(photocentreDataSource)
                replaceWith(branchOfficeCRUDFragment)
            }
        }
    }

}

class TotalBranchOffice(amount: Int) {
    var amount: Int by property(amount)
    fun amountProperty() = getProperty(TotalBranchOffice::amount)
}

class TotalBranchOfficeModel : ItemViewModel<TotalBranchOffice>() {
    val amount = bind(TotalBranchOffice::amountProperty)
}
