package photocentre.view

import tornadofx.*
import photocentre.controllers.KioskController
import photocentre.dao.KioskDao
import photocentre.main.PhotocentreDataSource
import photocentre.dataClasses.KioskModel
import photocentre.executors.KioskExecutor

class KioskCRUDFragment(photocentreDataSource: PhotocentreDataSource) : Fragment() {
    val kioskModel: KioskModel by inject()
    val kioskDao = KioskDao(photocentreDataSource)
    val kioskController = KioskController(KioskExecutor(photocentreDataSource, kioskDao))
    override val root = borderpane {
        center = vbox(10.0) {
            paddingAll = 10
            button("Create") {
                action {
                    val kioskCreateFragment = KioskCreateFragment(photocentreDataSource)
                    replaceWith(kioskCreateFragment)
                }
            }
            button("Update") {
                action {
                    val kioskDetailsFragment = KioskDetailsFragment(photocentreDataSource)
                    replaceWith(kioskDetailsFragment)
                }
            }
            button("countKiosks") {
                action {
                    val countKiosksFragment = KioskSelectCountFragment(photocentreDataSource)
                    replaceWith(countKiosksFragment)
                }
            }
        }
    }
}
