package photocentre.dao

import photocentre.dataClasses.Amateur
import java.sql.Statement
import javax.sql.DataSource

class AmateurDao(private val dataSource: DataSource) {

    fun createAmateur(toCreate: Amateur): Long {
        val statement = dataSource.connection.prepareStatement(
                "insert into amateurs (amateur_experience) values (?)",
                Statement.RETURN_GENERATED_KEYS
        )
        statement.setInt(1, toCreate.experience)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createAmateurs(toCreate: Iterable<Amateur>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
                "insert into amateurs (amateur_experience) values (?)",
                Statement.RETURN_GENERATED_KEYS
        )

        for (amateur in toCreate) {
            statement.setInt(1, amateur.experience)
            statement.addBatch()
        }

        statement.executeUpdate()
        val generated = statement.generatedKeys
        val res = ArrayList<Long>()
        while (generated.next()) {
            res += generated.getLong(1)
        }

        return res
    }

    fun findAmateur(id: Long): Amateur? {
        val statement = dataSource.connection.prepareStatement(
                "select amateur_id, amateur_experience from amateurs where amateur_id = ?"
        )
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Amateur(resultSet.getLong("amateur_id"), resultSet.getInt("amateur_experience"))
        } else {
            null
        }
    }

    fun updateAmateur(amateur: Amateur) {
        val statement = dataSource.connection.prepareStatement(
                "update amateurs set amateur_experience = ? where amateur_id = ?"
        )
        statement.setInt(1, amateur.experience)
        statement.setLong(2, amateur.id!!)
        statement.executeUpdate()
    }

    fun deleteAmateur(id: Long) {
        val statement = dataSource.connection.prepareStatement(
                "delete from amateurs where amateur_id = ?"
        )
        statement.setLong(1, id)
        statement.executeUpdate()
    }

}