package photocentre.executors

import photocentre.dao.ProfessionalDao
import photocentre.dataClasses.Professional
import photocentre.main.PhotocentreDataSource

class ProfessionalExecutor(
        private val dataSource: PhotocentreDataSource,
        private val professionalDao: ProfessionalDao
) {
    fun createProfessional(toCreate: Professional): Long {
        return transaction(dataSource) {
            professionalDao.createProfessional(toCreate)
        }
    }

    fun createProfessionals(toCreate: Iterable<Professional>): List<Long> {
        return transaction(dataSource) {
            professionalDao.createProfessionals(toCreate)
        }
    }

    fun findProfessional(id: Long): Professional? {
        return transaction(dataSource) {
            professionalDao.findProfessional(id)
        }
    }

    fun updateProfessional(professional: Professional) {
        return transaction(dataSource) {
            professionalDao.updateProfessional(professional)
        }
    }

    fun deleteProfessional(id: Long) {
        return transaction(dataSource) {
            professionalDao.deleteProfessional(id)
        }
    }
}