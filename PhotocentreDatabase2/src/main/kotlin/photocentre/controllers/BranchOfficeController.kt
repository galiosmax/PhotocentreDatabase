package photocentre.controllers

import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.Kiosk
import photocentre.executors.BranchOfficeExecutor

class BranchOfficeController(private val executor: BranchOfficeExecutor) {

    fun createBranchOffice(branchOffice: BranchOffice): BranchOffice {
        val id = executor.createBranchOffice(branchOffice)
        return BranchOffice(
                id = id, 
                address = branchOffice.address, 
                amountOfWorkers = branchOffice.amountOfWorkers
        )
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

    fun updateBranchOffice(branchOffice: BranchOffice): String {
        return executor.updateBranchOffice(branchOffice).toString()
    }

    fun deleteBranchOffice(id: Long): String {
        return executor.deleteBranchOffice(id).toString()
    }

    fun countBranchOffices(): Int {
        return executor.countBranchOffices()
    }

    fun getAllBranchOffices(): List<BranchOffice> {
        return executor.getAllBranchOffices()
    }

    fun getBranchOfficesAndKiosks(): List<Pair<BranchOffice, Kiosk>> {
        return executor.getBranchOfficesAndKiosks()
    }
}