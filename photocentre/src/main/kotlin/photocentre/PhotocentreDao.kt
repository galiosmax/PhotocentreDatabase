package photocentre

import java.sql.Statement
import javax.sql.DataSource

class PhotocentreDao(private val dataSource: DataSource) {

    fun createPhotocentre(name: String): Long {
        val statement = dataSource.connection.prepareStatement("INSERT INTO Photocentre (photocentre_name) VALUES(?)", Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, name)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

}