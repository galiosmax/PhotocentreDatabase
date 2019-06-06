package photocentre.controllers

import javafx.collections.FXCollections
import tornadofx.*
import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.Kiosk
import photocentre.executors.BranchOfficeExecutor

class BranchOfficeController(private val executor: BranchOfficeExecutor) {
//todo переделать другие контроллеры в таком стиле
//    fun createBranchOffice(branchOffice: BranchOffice): BranchOffice {
//        val id = executor.createBranchOffice(branchOffice)
//        return BranchOffice(
//                id = id,
//                address = branchOffice.address,
//                amountOfWorkers = branchOffice.amountOfWorkers
//        )
//    }
    fun createBranchOffice(address: String, amnt: Int): Long {
        val branchOffice = BranchOffice(-1, address, amnt)
        return executor.createBranchOffice(branchOffice)
    }

    fun createBranchOffices(branchOffices: List<BranchOffice>): List<BranchOffice> {
        val ids = executor.createBranchOffices(branchOffices)
        val newBranchOffices = ArrayList<BranchOffice>()

        for (i in 1..ids.size) {
            newBranchOffices += BranchOffice(
                    id = ids[i],
                    address = branchOffices[i].address,
                    amountOfWorkers = branchOffices[i].amountOfWorkers
            )
        }
        return newBranchOffices
    }

    fun getBranchOffice(id: Long): BranchOffice? {
        return executor.findBranchOffice(id)
    }

//    fun updateBranchOffice(branchOffice: BranchOffice): String {
//        return executor.updateBranchOffice(branchOffice).toString()
//    }
    fun updateBranchOffice(newOffice: BranchOffice) {
        executor.updateBranchOffice(newOffice)
    }

//    fun deleteBranchOffice(id: Long): String {
//        return executor.deleteBranchOffice(id).toString()
//    }
    fun deleteBranchOffice(id: Long) {
        executor.deleteBranchOffice(id)
    }

    fun countBranchOffices(): Int {
        return executor.countBranchOffices() ?: 0
    }

//    fun getAllBranchOffices(): List<BranchOffice> {
//        return executor.getAllBranchOffices()
//    }
    fun getBranchOffices(): MutableList<BranchOffice> {
        return FXCollections.observableArrayList(executor.getAllBranchOffices())
    }

    fun getBranchOfficesAndKiosks(): List<Pair<BranchOffice, Kiosk>> {
        return executor.getBranchOfficesAndKiosks()
    }















}
