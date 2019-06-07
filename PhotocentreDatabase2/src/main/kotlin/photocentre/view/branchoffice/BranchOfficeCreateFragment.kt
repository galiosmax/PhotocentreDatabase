package photocentre.view.branchoffice

import javafx.scene.control.TextField
import photocentre.controllers.BranchOfficeController
import photocentre.executors.BranchOfficeExecutor
import photocentre.main.PhotocentreDataSource
import photocentre.dao.BranchOfficeDao
import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.BranchOfficeModel
import tornadofx.*
import java.lang.NumberFormatException
import kotlin.math.max

class BranchOfficeCreateFragment(photocentreDataSource: PhotocentreDataSource) : Fragment() {
    val branchOfficeModel: BranchOfficeModel by inject()
    val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    val branchOfficeController = BranchOfficeController(BranchOfficeExecutor(photocentreDataSource, branchOfficeDao))
    var currentAddressText: TextField by singleAssign()
    var currentAmount: Int by singleAssign()
    override val root = form {
        fieldset("Create Branch Office") {

            field("Address") {
                currentAddressText = textfield()
            }
            field("Amount of workers") {
                val currentAmountText = textfield()
                val amount = try {
                    currentAmountText.text.toInt()
                } catch (e: NumberFormatException) {
                    0
                }
                currentAmount = max(0, amount)
            }
        }
        buttonbar {
            button("Save") {
                action {

                    branchOfficeModel.branchOffices.get().add(branchOfficeController.createBranchOffice(BranchOffice(
                            address = currentAddressText.text,
                            amountOfWorkers = currentAmount
                    )))
                    currentAddressText.text = ""
                    currentAmount = 0
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