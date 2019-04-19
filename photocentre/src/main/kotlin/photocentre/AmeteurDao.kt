package photocentre

import java.sql.Statement
import javax.sql.DataSource

class AmeteurDao(private val dataSource: DataSource) {
    
    fun createAmateur(toCreate: Amateur): Long {
        val statement = dataSource.connection.prepareStatement("INSERT INTO Amateurs (amateur_experience) VALUES (?)", Statement.RETURN_GENERATED_KEYS)
        statement.setInt(1, toCreate.experience)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createAmateurs(toCreate: Iterable<Amateur>): List<Long> {
        val statement = dataSource.connection.prepareStatement("INSERT INTO Amateurs (amateur_experience) VALUES(?)", Statement.RETURN_GENERATED_KEYS)

        for (amateur in toCreate) {
            statement.setInt(1, amateur.experience)
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

    fun findAmateur(id: Long): Amateur? {
        val statement = dataSource.connection.prepareStatement("SELECT amateur_id, amateur_experience FROM Amateurs WHERE amateur_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Amateur(resultSet.getLong("amateur_id"), resultSet.getInt("amateur_experience"))
        } else {
            null
        }
    }

    fun updateAmateur(amateur: Amateur) {
        val statement = dataSource.connection.prepareStatement("UPDATE Amateurs SET amateur_experience = ? WHERE amateur_id = ?")
        statement.setInt(1, amateur.experience)
        statement.setLong(2, amateur.id!!)
        statement.executeUpdate()
    }
}