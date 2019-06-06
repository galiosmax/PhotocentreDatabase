package photocentre.executors

import photocentre.dao.FilmDao
import photocentre.dataClasses.Film
import photocentre.main.PhotocentreDataSource

class FilmExecutor(
        private val dataSource: PhotocentreDataSource,
        private val filmDao: FilmDao
) {
    fun createFilm(toCreate: Film): Long {
        return transaction(dataSource) {
            filmDao.createFilm(toCreate)
        }
    }

    fun createFilms(toCreate: Iterable<Film>): List<Long> {
        return transaction(dataSource) {
            filmDao.createFilms(toCreate)
        }
    }

    fun findFilm(id: Long): Film? {
        return transaction(dataSource) {
            filmDao.findFilm(id)
        }
    }

    fun updateFilm(film: Film) {
        return transaction(dataSource) {
            filmDao.updateFilm(film)
        }
    }

    fun deleteFilm(id: Long) {
        return transaction(dataSource) {
            filmDao.deleteFilm(id)
        }
    }
}