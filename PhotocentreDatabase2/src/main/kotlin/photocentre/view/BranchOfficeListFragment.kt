package photocentre.view

import javafx.beans.binding.BooleanExpression
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.Parent
import javafx.scene.control.ContextMenu
import photocentre.controller.BranchOfficeController
import photocentre.controller.Executor
import photocentre.controller.PhotocentreDataSource
import photocentre.dao.BranchOfficeDao
import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.BranchOfficeModel
import tornadofx.*
import javafx.scene.control.TableView
import tornadofx.Stylesheet.Companion.contextMenu

class BranchOfficeListFragment(photocentreDataSource: PhotocentreDataSource) : Fragment() {
    //val photocentreDataSource : PhotocentreDataSource by param()
    val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    val branchOfficeCtrl = BranchOfficeController(Executor(photocentreDataSource, branchOfficeDao))
    //val branchOfficeCtrl : BranchOfficeController by inject()
    val branchOfficeModel: BranchOfficeModel by inject()
    //var tbl: TableView<photocentre.dataClasses.BranchOffice> by singleAssign()
    init {
        branchOfficeModel.branchOffices.set(branchOfficeCtrl.getBranchOffices().asObservable())
    }
//    override val savable = SimpleBooleanProperty(false)
//    override val closeable = SimpleBooleanProperty(false)
//    override val creatable = SimpleBooleanProperty(false)
    //    override fun onRefresh() {
//        with(workspace) {
//            hbox(4.0) {
//                progressbar(branchOfficeModel.taskStatus.progress) {
//                    label(branchOfficeModel.taskStatus.message)
//                    hiddenWhen { branchOfficeModel.taskStatus.running }
//                    paddingAll = 4
//                }
//            }
//        }
//    }


    override val root = borderpane {

        center = tableview(branchOfficeModel.branchOffices) {
        //center = tableview(branchOfficeCtrl.offices.asObservable()) {

            column("ID", BranchOffice::idProperty)
            column("Address", BranchOffice::addressProperty)
            column("Amount of workers", BranchOffice::amountOfWorkersProperty)
            bindSelected(branchOfficeModel)
            contextmenu {
                item("Delete").action {
                    //branchOfficeModel.branchOffices
                    branchOfficeCtrl.deleteBranchOffice(selectedItem?.id!!)
                    branchOfficeModel.branchOffices.set(branchOfficeCtrl.getBranchOffices().asObservable())
                }
            }
//            onUserSelect {
//                //var branchOfficeDetailsFragment = BranchOfficeDetailsFragment(branchOfficeCtrl)
//                workspace.dock<BranchOfficeDetailsFragment>()
//            }
            //enableCellEditing()
            //enableDirtyTracking()
            //regainFocusAfterEdit()
            columnResizePolicy = SmartResize.POLICY
        }

//        bottom = hbox(4.0) {
//            paddingAll = 10
//            button("Create") {
//
//            }
//            button("selectBranchOfficesAndKiosks") {
//
//            }
//        }
    }
}