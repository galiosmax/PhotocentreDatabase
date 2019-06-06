package photocentre.executors

import photocentre.dao.SupplierDao
import photocentre.dataClasses.Supplier
import photocentre.main.PhotocentreDataSource

class SupplierExecutor(
        private val dataSource: PhotocentreDataSource,
        private val supplierDao: SupplierDao
) {
    fun createSupplier(toCreate: Supplier): Long {
        return transaction(dataSource) {
            supplierDao.createSupplier(toCreate)
        }
    }

    fun createSuppliers(toCreate: Iterable<Supplier>): List<Long> {
        return transaction(dataSource) {
            supplierDao.createSuppliers(toCreate)
        }
    }

    fun findSupplier(id: Long): Supplier? {
        return transaction(dataSource) {
            supplierDao.findSupplier(id)
        }
    }

    fun updateSupplier(supplier: Supplier) {
        return transaction(dataSource) {
            supplierDao.updateSupplier(supplier)
        }
    }

    fun deleteSupplier(id: Long) {
        return transaction(dataSource) {
            supplierDao.deleteSupplier(id)
        }
    }
}