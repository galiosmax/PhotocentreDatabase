package photocentre.view

import javafx.beans.binding.BooleanExpression
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.control.TabPane
import javafx.scene.control.TableView
import photocentre.controller.BranchOfficeController
import photocentre.controller.Db
import photocentre.controller.Executor
import photocentre.controller.PhotocentreDataSource
import photocentre.dao.BranchOfficeDao
import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.BranchOfficeModel
import tornadofx.*

class MainView : View("Photocentre Database") {
    val db = Db()
    val photocentreDataSource = PhotocentreDataSource(db.dataSource)
    //val status: TaskStatus by inject()
    //val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    //val branchOfficeCtrl : BranchOfficeController by param(BranchOfficeController(Executor(photocentreDataSource, branchOfficeDao)))
    //val branchOfficeModel: BranchOfficeModel by inject()
    //override val savable = SimpleBooleanProperty(false)
    //override val deletable = SimpleBooleanProperty(false)
    //override val creatable = SimpleBooleanProperty(false)
    //val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    //val branchOfficeCtrl = BranchOfficeController(Executor(photocentreDataSource, branchOfficeDao))
    //val branchOfficeModel: BranchOfficeModel by inject()

//    override fun onDock() {
//        with(workspace) {
//            hbox(4.0) {
//                progressbar(status.progress) {
//                    label(status.message)
//                    visibleWhen { status.running }
//                    paddingAll = 4
//                }
//            }
//        }
//    }

    override val root = tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        //prefHeight = 600.0
        tab("Offices") {
            splitpane {
                add(BranchOfficeListFragment(photocentreDataSource))
                add(BranchOfficeCRUDFragment(photocentreDataSource))
                //add(BranchOfficeDetailsFragment(photocentreDataSource))
            }
        }
        tab("Kiosks") {
            add(KioskListFragment())
        }
//            borderpane {
//
//                center = tableview(branchOfficeCtrl.getBranchOffices()) {
//                    column("ID", BranchOffice::idProperty)
//                    column("Address", BranchOffice::addressProperty)
//                    column("Amnt of Workers", BranchOffice::amountOfWorkersProperty)
//                    bindSelected(branchOfficeModel)
//                }
//
//                right = vbox(10.0) {
//                    label("")
//                    buttonbar {
//                        button("selectBranchOfficesAndKiosks") {
//
//                        }
//                    }
//                }
//            }

//                right = buttonbar {
//                    borderpane {
//                        top = button("selectBranchOfficesAndKiosks") {
//
//                        }
//                    }
//                }


//        tab("Kiosks") {
//            vbox {
//
//            }
//        }
//        tab("Workers") {
//            vbox {
//
//            }
//        }
//        tab("Customers") {
//            vbox {
//
//            }
//        }
//        tab("Amateurs") {
//            vbox {
//
//            }
//        }
//        tab("Professionals") {
//            vbox {
//
//            }
//        }
//        tab("Orders") {
//            vbox {
//
//            }
//        }
//        tab("Photos") {
//            vbox {
//
//            }
//        }
//        tab("Films") {
//            vbox {
//
//            }
//        }
//        tab("Services") {
//            vbox {
//
//            }
//        }
//        tab("Office Items") {
//            vbox {
//
//            }
//        }
//        tab("Sold Items") {
//            vbox {
//
//            }
//        }
//        tab("Supply Items") {
//            vbox {
//
//            }
//        }
//        tab("Supplies") {
//            vbox {
//
//            }
//        }
//        tab("Suppliers") {
//            vbox {
//
//            }
//        }

    }

}
