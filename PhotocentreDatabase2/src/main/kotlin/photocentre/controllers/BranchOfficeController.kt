package photocentre.controllers

import javafx.collections.FXCollections
import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.Kiosk
import photocentre.executors.BranchOfficeExecutor
import kotlin.math.max

class BranchOfficeController(private val executor: BranchOfficeExecutor) {
    //todo переделать другие контроллеры в таком стиле
    //todo тут лучше валидацию делать наверн
    fun createBranchOffice(branchOffice: BranchOffice): BranchOffice {

        branchOffice.amountOfWorkers = max(0, branchOffice.amountOfWorkers)
        val id = executor.createBranchOffice(branchOffice)

        return BranchOffice(
                id = id,
                address = branchOffice.address,
                amountOfWorkers = branchOffice.amountOfWorkers
        )
    }
//    fun createBranchOffice(address: String, amount: Int): Long {
//        val branchOffice = BranchOffice(-1, address, amount)
//        return executor.createBranchOffice(branchOffice)
//    }

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

    fun updateBranchOffice(newOffice: BranchOffice) {
        executor.updateBranchOffice(newOffice)
    }

    fun deleteBranchOffice(id: Long) {
        executor.deleteBranchOffice(id)
    }

    fun countBranchOffices(): Int {
        return executor.countBranchOffices() ?: 0
    }

    fun getBranchOffices(): MutableList<BranchOffice> {
        return FXCollections.observableArrayList(executor.getAllBranchOffices())
    }

    fun getBranchOfficesAndKiosks(): List<Pair<BranchOffice, Kiosk>> {
        return executor.getBranchOfficesAndKiosks()
    }


}
