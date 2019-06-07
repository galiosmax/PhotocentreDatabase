package photocentre.view

import javafx.scene.control.TextField
import photocentre.controllers.BranchOfficeController
import photocentre.executors.BranchOfficeExecutor
import photocentre.main.PhotocentreDataSource
import photocentre.dao.BranchOfficeDao
import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.BranchOfficeModel
import tornadofx.*
import java.lang.NumberFormatException

class BranchOfficeCreateFragment(photocentreDataSource: PhotocentreDataSource) : Fragment() {
    val branchOfficeModel: BranchOfficeModel by inject()
    val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    val branchOfficeController = BranchOfficeController(BranchOfficeExecutor(photocentreDataSource, branchOfficeDao))
    var currentAddressText: TextField by singleAssign()
    var currentAmountText: TextField by singleAssign()
    override val root = form {
        fieldset("Create Branch Office") {
            //todo можно валидацию сюда добавить
            //todo валидация должна быть в контроллерах по идее
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

                    //ну типа валидация хыы, наверное лучше все таки там, когда ввод, но я нинаю как, потом объясни
                    val branchOffice = try {
                        BranchOffice(
                                address = currentAddressText.text,
                                amountOfWorkers = currentAmountText.text.toInt()
                        )
                    } catch (e: NumberFormatException) {
                        BranchOffice(
                                address = currentAddressText.text
                        )
                    }

                    branchOfficeModel.branchOffices.get().add(branchOfficeController.createBranchOffice(branchOffice))
                    //branchOfficeModel.branchOffices.set(branchOfficeController.getBranchOffices().asObservable())
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