package photocentre.view.kiosk

import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import photocentre.controllers.BranchOfficeController
import photocentre.controllers.KioskController
import photocentre.dao.BranchOfficeDao
import photocentre.dao.KioskDao
import photocentre.dataClasses.BranchOfficeModel
import photocentre.dataClasses.Kiosk
import photocentre.dataClasses.KioskModel
import photocentre.executors.BranchOfficeExecutor
import photocentre.executors.KioskExecutor
import photocentre.main.PhotocentreDataSource
import tornadofx.*

class KioskCreateFragment(photocentreDataSource: PhotocentreDataSource) : Fragment() {
    val kioskModel: KioskModel by inject()
    val branchOfficeModel: BranchOfficeModel by inject()
    val kioskDao = KioskDao(photocentreDataSource)
    val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    val kioskController = KioskController(KioskExecutor(photocentreDataSource, kioskDao))
    val branchOfficeController = BranchOfficeController(BranchOfficeExecutor(photocentreDataSource, branchOfficeDao))
    var currentAddressText: TextField by singleAssign()
    var currentAmountText: TextField by singleAssign()
    var currentOfficeAddress: ComboBox<String> by singleAssign()
    override val root = form {
        fieldset("Create Branch Kiosk") {
            //todo можно валидацию сюда добавить
            //todo валидация должна быть в контроллерах по идее
            field("Address") {
                currentAddressText = textfield()
            }
            field("Amount of workers") {
                currentAmountText = textfield()
            }
            field("Branch office address") {
                currentOfficeAddress = combobox {
                    items = kioskController.getAllBranchOfficeAddresses().asObservable()
                }
            }
        }
        buttonbar {
            button("Save") {
                action {
                    //todo а почему просто вот так не сделать? Удобнее же
                    var index = 0
                    for (i in 1..branchOfficeModel.branchOffices.get().size) {
                        if (branchOfficeModel.branchOffices.get()[i].address == currentOfficeAddress.value)
                            index = i
                    }
                    //ну типа валидация хыы, наверное лучше все таки там, когда ввод, но я нинаю как, потом объясни
                    val kiosk: Kiosk = try {
                        Kiosk(
                                address = currentAddressText.text,
                                amountOfWorkers = currentAmountText.text.toInt(),
                                branchOffice = branchOfficeModel.branchOffices.get()[index]
                        )
                    } catch (e: NumberFormatException) {
                        Kiosk(
                                address = currentAddressText.text,
                                branchOffice = branchOfficeModel.branchOffices.get()[index]
                        )
                        //alert(Alert.AlertType.WARNING, "Error", "Incorrect input")
                    }

                    kioskModel.kiosks.get().add(kioskController.createKiosk(kiosk))
                    //branchOfficeModel.branchOffices.set(branchOfficeController.getBranchOffices().asObservable())
                    currentAddressText.text = ""
                    currentAmountText.text = ""
                }
            }
        }
        button("Back") {
            action {
                val kioskCRUDFragment = KioskCRUDFragment(photocentreDataSource)
                replaceWith(kioskCRUDFragment, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
            }
        }
    }
}
