package photocentre

import java.lang.Exception

class Executor(private val dataSource: PhotocentreDataSource,
               private val photocentreDao: PhotocentreDao,
               private val branchOfficeDao: BranchOfficeDao
) {

    fun createPhotocentre(toCreate: Photocentre) : Long {
        return transaction(dataSource) {
            photocentreDao.createPhotocentre(toCreate)
        }
    }

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

}

fun <T> transaction(dataSource: PhotocentreDataSource, body: () -> T) : T {
    dataSource.realGetConnection().use {
        it.autoCommit = false
        dataSource.connection = it
        try {
            val res = body()
            it.commit()
            return res
        } catch (e: Exception) {
            it.rollback()
            throw e
        }
    }
}