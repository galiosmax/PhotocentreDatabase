package photocentre.executors

import photocentre.dao.*
import photocentre.dataClasses.BranchOffice
import photocentre.main.PhotocentreDataSource

class BranchOfficeExecutor(
        private val dataSource: PhotocentreDataSource,
        private val branchOfficeDao: BranchOfficeDao
) {
    fun createBranchOffice(toCreate: BranchOffice): Long {
        return transaction(dataSource) {
            branchOfficeDao.createBranchOffice(toCreate)
        }
    }

    fun createBranchOffices(toCreate: Iterable<BranchOffice>): List<Long> {
        return transaction(dataSource) {
            branchOfficeDao.createBranchOffices(toCreate)
        }
    }

    fun findBranchOffice(id: Long): BranchOffice? {
        return transaction(dataSource) {
            branchOfficeDao.findBranchOffice(id)
        }
    }

    fun updateBranchOffice(branchOffice: BranchOffice) {
        return transaction(dataSource) {
            branchOfficeDao.updateBranchOffice(branchOffice)
        }
    }

    fun deleteBranchOffice(id: Long) {
        return transaction(dataSource) {
            branchOfficeDao.deleteBranchOffice(id)
        }
    }
}