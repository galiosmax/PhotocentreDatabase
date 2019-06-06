package photocentre.view

import javafx.scene.Parent
import javafx.scene.control.TextField
import photocentre.controller.BranchOfficeController
import photocentre.controller.Executor
import photocentre.controller.PhotocentreDataSource
import photocentre.dao.BranchOfficeDao
import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.BranchOfficeModel
import tornadofx.*

class BranchOfficeCreateFragment(photocentreDataSource: PhotocentreDataSource) : Fragment() {
    val branchOfficeModel: BranchOfficeModel by inject()
    //val photocentreDataSource: PhotocentreDataSource by param()
    val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    val branchOfficeController = BranchOfficeController(Executor(photocentreDataSource, branchOfficeDao))
    var currentAddressText: TextField by singleAssign()
    var currentAmountText: TextField by singleAssign()
    //    var currentID: Long = 0
//    var currentAddress: String = ""
//    var currentAmount: Int = 0
    //override val savable = branchOfficeModel.dirty
    //    init {
//        branchOfficeModel.branchOffices.set(branchOfficeController.getBranchOffices().asObservable())
//    }
    override val root = form {
        //        label("ID")
//        val tempInt = branchOfficeModel.id.value
//        val strID = tempInt.toString()
//        textfield(branchOfficeModel.id.value.toString()) {
//            //editableProperty().set(false)
//            disableProperty().set(true)
//        }
        //currentAddressText = textfield()
        fieldset("Create Branch Office") {
            field("Address") {
                currentAddressText = textfield()
            }
            field("Amount of workers") {
                currentAmountText = textfield()
            }
        }
//        label("Address")
//        textfield().validator {
//
//        }
//        label("Amount of workers")
//        textfield()

        buttonbar {
            button("Save") {
                action {
                    branchOfficeController.createBranchOffice(currentAddressText.text, currentAmountText.text.toInt())
                    branchOfficeModel.branchOffices.set(branchOfficeController.getBranchOffices().asObservable())
                    currentAddressText.text = ""
                    currentAmountText.text = ""
                }
            }

//            button("Undo") {
//                enableWhen(branchOfficeModel.dirty)
//                action {
//                    branchOfficeModel.rollback()
//                }
//            }
        }
        button("Back") {
            action {
                val branchOfficeCRUDFragment = BranchOfficeCRUDFragment(photocentreDataSource)
                replaceWith(branchOfficeCRUDFragment)
            }
        }

    }

//    override fun onSave() {
//
//        //todo
//        branchOfficeModel.commit()
//        {
//            currentID = branchOfficeModel.id.value
//            currentAddress = branchOfficeModel.address.value
//            currentAmount = branchOfficeModel.amountOfWorkers.value
//            branchOfficeController.updateBranchOffice(BranchOffice(currentID, currentAddress, currentAmount))
//        }
////        currentID = branchOfficeModel.id.value
////        currentAddress = branchOfficeModel.address.value
////        currentAmount = branchOfficeModel.amountOfWorkers.value
////        branchOfficeController.updateBranchOffice(BranchOffice(currentID, currentAddress, currentAmount))
//        //branchOfficeModel.commit {
//        //  ctrl.commit()
//        //}
//    }

//    override fun onDelete() {
//        branchOfficeModel.rollback()
//    }
}