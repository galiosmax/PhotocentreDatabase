package photocentre.executors

import photocentre.dao.OrderDao
import photocentre.dataClasses.Order
import photocentre.main.PhotocentreDataSource

class OrderExecutor(
        private val dataSource: PhotocentreDataSource,
        private val orderDao: OrderDao
) {
    fun createOrder(toCreate: Order): Long {
        return transaction(dataSource) {
            orderDao.createOrder(toCreate)
        }
    }

    fun createOrders(toCreate: Iterable<Order>): List<Long> {
        return transaction(dataSource) {
            orderDao.createOrders(toCreate)
        }
    }

    fun findOrder(id: Long): Order? {
        return transaction(dataSource) {
            orderDao.findOrder(id)
        }
    }

    fun updateOrder(order: Order) {
        return transaction(dataSource) {
            orderDao.updateOrder(order)
        }
    }

    fun deleteOrder(id: Long) {
        return transaction(dataSource) {
            orderDao.deleteOrder(id)
        }
    }
}