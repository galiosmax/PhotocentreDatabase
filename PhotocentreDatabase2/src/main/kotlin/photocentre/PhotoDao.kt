package photocentre

import java.sql.Statement
import javax.sql.DataSource

class PhotoDao(private val dataSource: DataSource) {

    fun createPhoto(toCreate: Photo): Long {
        val statement = dataSource.connection.prepareStatement("INSERT INTO photos (photo_paper_type, photo_film, photo_format, order_id) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, toCreate.paperType)
        statement.setString(2, toCreate.film)
        statement.setString(3, toCreate.format)
        statement.setLong(4, toCreate.order.id!!)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createPhotos(toCreate: Iterable<Photo>): List<Long> {
        val statement = dataSource.connection.prepareStatement("INSERT INTO photos (photo_paper_type, photo_film, photo_format, order_id) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)

        for (photo in toCreate) {
            statement.setString(1, photo.paperType)
            statement.setString(2, photo.film)
            statement.setString(3, photo.format)
            statement.setLong(4, photo.order.id!!)
            statement.addBatch()
        }

        statement.executeUpdate()
        val generated = statement.generatedKeys
        val res = ArrayList<Long>()
        while(generated.next()) {
            res += generated.getLong(1)
        }

        return res
    }

    //TODO LAZY LOADING
    fun findPhoto(id: Long): Photo? {
        val statement = dataSource.connection.prepareStatement("SELECT photo_id, photo_paper_type, photo_film, photo_format, order_id FROM photos WHERE photo_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Photo(resultSet.getLong("photo_id"),
                    resultSet.getString("photo_paper_type"),
                    resultSet.getString("photo_film"),
                    resultSet.getString("photo_format"),
                    Order(resultSet.getLong("order_id"), ))
        } else {
            null
        }
    }

    fun updatePhoto(photo: Photo) {
        val statement = dataSource.connection.prepareStatement("UPDATE photos SET photo_paper_type = ?, photo_film = ?, photo_format = ?, order_id = ? WHERE photo_id = ?")
        statement.setString(1, photo.paperType)
        statement.setString(2, photo.film)
        statement.setString(3, photo.format)
        statement.setLong(4, photo.order.id!!)
        statement.setLong(5, photo.id!!)
        statement.executeUpdate()
    }

    fun deletePhoto(id: Long) {
        val statement = dataSource.connection.prepareStatement("DELETE FROM photos WHERE photo_id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }
}