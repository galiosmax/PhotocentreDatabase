package photocentre.view

import photocentre.controllers.BranchOfficeController
import photocentre.executors.BranchOfficeExecutor
import photocentre.main.PhotocentreDataSource
import photocentre.dao.BranchOfficeDao
import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.BranchOfficeModel
import tornadofx.*

class BranchOfficeDetailsFragment(photocentreDataSource: PhotocentreDataSource) : Fragment() {
    val branchOfficeModel: BranchOfficeModel by inject()
    val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    val branchOfficeController = BranchOfficeController(BranchOfficeExecutor(photocentreDataSource, branchOfficeDao))
    var currentID: Long = 0
    var currentAddress: String = ""
    var currentAmount: Int = 0
    override val savable = branchOfficeModel.dirty
    override val root = vbox {
        label("Address")
        textfield(branchOfficeModel.address)
        label("Amount of workers")
        textfield(branchOfficeModel.amountOfWorkers)

        buttonbar {
            button("Save") {
                enableWhen(branchOfficeModel.dirty)
                action {
                    currentID = branchOfficeModel.id.value
                    currentAddress = branchOfficeModel.address.value
                    currentAmount = branchOfficeModel.amountOfWorkers.value
                    branchOfficeController.updateBranchOffice(BranchOffice(currentID, currentAddress, currentAmount))
                    branchOfficeModel.branchOffices.set(branchOfficeController.getBranchOffices().asObservable())
                }
            }
            button("Undo") {
                enableWhen(branchOfficeModel.dirty)
                action {
                    branchOfficeModel.rollback()
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