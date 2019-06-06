package photocentre.executors

import photocentre.dao.AmateurDao
import photocentre.dataClasses.Amateur
import photocentre.main.PhotocentreDataSource

class AmateurExecutor(
        private val dataSource: PhotocentreDataSource,
        private val amateurDao: AmateurDao
) {
    fun createAmateur(toCreate: Amateur): Long {
        return transaction(dataSource) {
            amateurDao.createAmateur(toCreate)
        }
    }

    fun createAmateurs(toCreate: Iterable<Amateur>): List<Long> {
        return transaction(dataSource) {
            amateurDao.createAmateurs(toCreate)
        }
    }

    fun findAmateur(id: Long): Amateur? {
        return transaction(dataSource) {
            amateurDao.findAmateur(id)
        }
    }

    fun updateAmateur(amateur: Amateur) {
        return transaction(dataSource) {
            amateurDao.updateAmateur(amateur)
        }
    }

    fun deleteAmateur(id: Long) {
        return transaction(dataSource) {
            amateurDao.deleteAmateur(id)
        }
    }

    fun getAllAmateurs(): List<Amateur> {
        return transaction(dataSource) {
            amateurDao.gelAll()
        }
    }
}