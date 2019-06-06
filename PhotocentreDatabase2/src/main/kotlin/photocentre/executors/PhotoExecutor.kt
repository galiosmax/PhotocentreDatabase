package photocentre.executors

import photocentre.dao.PhotoDao
import photocentre.dataClasses.Photo
import photocentre.main.PhotocentreDataSource

class PhotoExecutor(
        private val dataSource: PhotocentreDataSource,
        private val photoDao: PhotoDao
) {
    fun createPhoto(toCreate: Photo): Long {
        return transaction(dataSource) {
            photoDao.createPhoto(toCreate)
        }
    }

    fun createPhotos(toCreate: Iterable<Photo>): List<Long> {
        return transaction(dataSource) {
            photoDao.createPhotos(toCreate)
        }
    }

    fun findPhoto(id: Long): Photo? {
        return transaction(dataSource) {
            photoDao.findPhoto(id)
        }
    }

    fun updatePhoto(photo: Photo) {
        return transaction(dataSource) {
            photoDao.updatePhoto(photo)
        }
    }

    fun deletePhoto(id: Long) {
        return transaction(dataSource) {
            photoDao.deletePhoto(id)
        }
    }
}