package photocentre.view.branchoffice

import photocentre.controllers.BranchOfficeController
import photocentre.executors.BranchOfficeExecutor
import photocentre.main.PhotocentreDataSource
import photocentre.dao.BranchOfficeDao
import photocentre.dataClasses.BranchOfficeModel
import tornadofx.*

class BranchOfficeCRUDFragment(photocentreDataSource: PhotocentreDataSource) : Fragment() {
    val branchOfficeModel: BranchOfficeModel by inject()
    val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    val branchOfficeController = BranchOfficeController(BranchOfficeExecutor(photocentreDataSource, branchOfficeDao))
    override val root = borderpane {
        center = vbox(10.0) {
            paddingAll = 10
            button("Create") {
                action {
                    val branchOfficeCreateFragment = BranchOfficeCreateFragment(photocentreDataSource)
                    replaceWith(branchOfficeCreateFragment, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                }
            }
            button("Update") {
                action {
                    val branchOfficeDetailsFragment = BranchOfficeDetailsFragment(photocentreDataSource)
                    replaceWith(branchOfficeDetailsFragment, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                }
            }
            button("selectBranchOfficesAndKiosks") {
                action {
                    //todo какой-то запрос связанный с киоском
                    val selectBranchOfficeAndKiosksFragment = BranchOfficeSelectBranchOfficeAndKiosksFragment(photocentreDataSource)
                    replaceWith(selectBranchOfficeAndKiosksFragment, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                }
            }
            button("countBranchOffices") {
                action {
                    val countOfficeFragment = BranchOfficeSelectCountOfficeFragment(photocentreDataSource)
                    replaceWith(countOfficeFragment, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                }
            }
        }
    }
}
