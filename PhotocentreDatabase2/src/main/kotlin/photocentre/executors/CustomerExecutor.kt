package photocentre.executors

import photocentre.dao.CustomerDao
import photocentre.dataClasses.Customer
import photocentre.main.PhotocentreDataSource

class CustomerExecutor(
        private val dataSource: PhotocentreDataSource,
        private val customerDao: CustomerDao
) {
    fun createCustomer(toCreate: Customer): Long {
        return transaction(dataSource) {
            customerDao.createCustomer(toCreate)
        }
    }

    fun createCustomers(toCreate: Iterable<Customer>): List<Long> {
        return transaction(dataSource) {
            customerDao.createCustomers(toCreate)
        }
    }

    fun findCustomer(id: Long): Customer? {
        return transaction(dataSource) {
            customerDao.findCustomer(id)
        }
    }

    fun updateCustomer(customer: Customer) {
        return transaction(dataSource) {
            customerDao.updateCustomer(customer)
        }
    }

    fun deleteCustomer(id: Long) {
        return transaction(dataSource) {
            customerDao.deleteCustomer(id)
        }
    }
}