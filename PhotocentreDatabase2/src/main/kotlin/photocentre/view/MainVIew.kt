package photocentre.view

import javafx.scene.control.TabPane
import photocentre.main.Db
import photocentre.main.PhotocentreDataSource
import photocentre.view.branchoffice.BranchOfficeCRUDFragment
import photocentre.view.branchoffice.BranchOfficeListFragment
import photocentre.view.kiosk.KioskCRUDFragment
import photocentre.view.kiosk.KioskListFragment
import tornadofx.*

class MainView : View("Photocentre Database") {
    val db = Db()
    val photocentreDataSource = PhotocentreDataSource(db.dataSource)

    override val root = tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        tab("Offices") {
            splitpane {
                add(BranchOfficeListFragment(photocentreDataSource))
                add(BranchOfficeCRUDFragment(photocentreDataSource))
            }
        }
        tab("Kiosks") {
            splitpane {
                add(KioskListFragment(photocentreDataSource))
                add(KioskCRUDFragment(photocentreDataSource))
            }
        }

        //todo табы для таблиц
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
