package photocentre

import java.sql.Statement
import javax.sql.DataSource

class SupplyDao(private val dataSource: DataSource) {

    fun createSupply(toCreate: Supply): Long {
        val statement = dataSource.connection.prepareStatement(
            "INSERT INTO Supplies (supply_cost) VALUES (?)",
            Statement.RETURN_GENERATED_KEYS
        )
        statement.setFloat(1, toCreate.cost)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createSupplys(toCreate: Iterable<Supply>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
            "INSERT INTO Supplies (supply_cost) VALUES (?)",
            Statement.RETURN_GENERATED_KEYS
        )

        for (supply in toCreate) {
            statement.setFloat(1, supply.cost)
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

    fun findSupply(id: Long): Supply? {
        val statement =
            dataSource.connection.prepareStatement("SELECT supply_id, supply_cost FROM Supplies WHERE supply_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Supply(
                resultSet.getLong("supply_id"),
                resultSet.getFloat("supply_cost")
            )
        } else {
            null
        }
    }

    fun updateSupply(supply: Supply) {
        val statement =
            dataSource.connection.prepareStatement("UPDATE Supplies SET supply_cost = ? WHERE supply_id = ?")
        statement.setFloat(1, supply.cost)
        statement.setLong(2, supply.id!!)
        statement.executeUpdate()
    }
}