package photocentre.view

import javafx.beans.binding.BooleanExpression
import photocentre.controller.BranchOfficeController
import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.BranchOfficeModel
import tornadofx.*

class BranchOfficeDetailsFragment() : Fragment() {
    val branchOfficeModel: BranchOfficeModel by inject()
    val branchOfficeController: BranchOfficeController by inject()
    var currentID : Long = 0
    var currentAddress : String = ""
    var currentAmount : Int = 0
    override val savable = branchOfficeModel.dirty

    override val root = vbox {
        label("ID")
        var idText = textfield(branchOfficeModel.id.value.toString()) {
            editableProperty().set(false)
            disableProperty().set(true)
        }
        label("Address")
        textfield(branchOfficeModel.address)
        label("Amount of workers")
        textfield(branchOfficeModel.amountOfWorkers)
    }

    override fun onSave() {

        //todo
        branchOfficeModel.commit()
        {
            currentID = branchOfficeModel.id.value
            currentAddress = branchOfficeModel.address.value
            currentAmount = branchOfficeModel.amountOfWorkers.value
            branchOfficeController.updateBranchOffice(BranchOffice(currentID, currentAddress, currentAmount))
        }
//        currentID = branchOfficeModel.id.value
//        currentAddress = branchOfficeModel.address.value
//        currentAmount = branchOfficeModel.amountOfWorkers.value
//        branchOfficeController.updateBranchOffice(BranchOffice(currentID, currentAddress, currentAmount))
        //branchOfficeModel.commit {
        //  ctrl.commit()
        //}
    }

    override fun onDelete() {
        branchOfficeModel.rollback()
    }
}