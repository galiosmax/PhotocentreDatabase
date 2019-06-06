package photocentre.view

import photocentre.controller.BranchOfficeController
import photocentre.controller.Executor
import photocentre.controller.PhotocentreDataSource
import photocentre.dao.BranchOfficeDao
import photocentre.dataClasses.BranchOfficeModel
import tornadofx.*

class BranchOfficeCRUDFragment(photocentreDataSource: PhotocentreDataSource) : Fragment() {
    val branchOfficeModel: BranchOfficeModel by inject()
    //val branchOfficeModel: BranchOfficeModel by inject()
    //val photocentreDataSource: PhotocentreDataSource by param()
    val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    val branchOfficeController = BranchOfficeController(Executor(photocentreDataSource, branchOfficeDao))
    override val root = borderpane {
        center = vbox(10.0) {
            paddingAll = 10
            button("Create") {
                action {
                    val branchOfficeCreateFragment = BranchOfficeCreateFragment(photocentreDataSource)
                    replaceWith(branchOfficeCreateFragment)
                }
            }
            button("Update") {
                action {
                    val branchOfficeDetailsFragment = BranchOfficeDetailsFragment(photocentreDataSource)
                    replaceWith(branchOfficeDetailsFragment)
                }
            }
            button("selectBranchOfficesAndKiosks") {
                action {

                }
            }
            button("countBranchOffices") {
                action {
                    val countOfficeFragment = BranchOfficeSelectCountOfficeFragment(photocentreDataSource)
                    replaceWith(countOfficeFragment)
                }
            }
        }
    }
}
