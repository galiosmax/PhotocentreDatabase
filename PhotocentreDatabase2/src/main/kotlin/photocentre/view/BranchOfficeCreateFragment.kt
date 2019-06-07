package photocentre.view

import javafx.scene.control.TextField
import photocentre.controllers.BranchOfficeController
import photocentre.executors.BranchOfficeExecutor
import photocentre.main.PhotocentreDataSource
import photocentre.dao.BranchOfficeDao
import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.BranchOfficeModel
import tornadofx.*

class BranchOfficeCreateFragment(photocentreDataSource: PhotocentreDataSource) : Fragment() {
    val branchOfficeModel: BranchOfficeModel by inject()
    val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    val branchOfficeController = BranchOfficeController(BranchOfficeExecutor(photocentreDataSource, branchOfficeDao))
    var currentAddressText: TextField by singleAssign()
    var currentAmountText: TextField by singleAssign()
    override val root = form {
        fieldset("Create Branch Office") {
            //todo можно валидацию сюда добавить
            field("Address") {
                currentAddressText = textfield()
            }
            field("Amount of workers") {
                currentAmountText = textfield()
            }
        }
        buttonbar {
            button("Save") {
                action {
                    //todo а почему просто вот так не сделать? Удобнее же
                    branchOfficeController.createBranchOffice(
                            BranchOffice(
                                    address = currentAddressText.text,
                                    amountOfWorkers = currentAmountText.text.toInt()
                            ))
                    branchOfficeModel.branchOffices.set(branchOfficeController.getBranchOffices().asObservable())
                    currentAddressText.text = ""
                    currentAmountText.text = ""
                }
            }
        }
        button("Back") {
            action {
                val branchOfficeCRUDFragment = BranchOfficeCRUDFragment(photocentreDataSource)
                replaceWith(branchOfficeCRUDFragment)
            }
        }
    }
}