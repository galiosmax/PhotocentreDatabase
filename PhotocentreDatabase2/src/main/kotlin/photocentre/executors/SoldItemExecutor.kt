package photocentre.executors

import photocentre.dao.SoldItemDao
import photocentre.dataClasses.SoldItem
import photocentre.main.PhotocentreDataSource

class SoldItemExecutor(
        private val dataSource: PhotocentreDataSource,
        private val soldItemDao: SoldItemDao
) {
    fun createSoldItem(toCreate: SoldItem): Long {
        return transaction(dataSource) {
            soldItemDao.createSoldItem(toCreate)
        }
    }

    fun createSoldItems(toCreate: Iterable<SoldItem>): List<Long> {
        return transaction(dataSource) {
            soldItemDao.createSoldItems(toCreate)
        }
    }

    fun findSoldItem(id: Long): SoldItem? {
        return transaction(dataSource) {
            soldItemDao.findSoldItem(id)
        }
    }

    fun updateSoldItem(soldItem: SoldItem) {
        return transaction(dataSource) {
            soldItemDao.updateSoldItem(soldItem)
        }
    }

    fun deleteSoldItem(id: Long) {
        return transaction(dataSource) {
            soldItemDao.deleteSoldItem(id)
        }
    }
}