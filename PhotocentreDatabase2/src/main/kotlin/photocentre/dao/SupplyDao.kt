package photocentre.dao

import photocentre.dataClasses.Supplier
import photocentre.dataClasses.Supply
import java.sql.Statement
import javax.sql.DataSource

class SupplyDao(private val dataSource: DataSource) {

    fun createSupply(toCreate: Supply): Long {
        val statement = dataSource.connection.prepareStatement(
            "INSERT INTO supplies (supply_cost, supplier_id) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        statement.setFloat(1, toCreate.cost)
        statement.setLong(2, toCreate.supplier.id!!)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createSupplies(toCreate: Iterable<Supply>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
            "INSERT INTO supplies (supply_cost, supplier_id) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )

        for (supply in toCreate) {
            statement.setFloat(1, supply.cost)
            statement.setLong(2, supply.supplier.id!!)
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

    //TODO LAZY LOADING
    fun findSupply(id: Long): Supply? {
        val statement =
            dataSource.connection.prepareStatement("SELECT supply_id, supply_cost, supplier_id FROM supplies WHERE supply_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Supply(
                    resultSet.getLong("supply_id"),
                    resultSet.getFloat("supply_cost"),
                    Supplier(resultSet.getLong("supplier_id"), )
            )
        } else {
            null
        }
    }

    fun updateSupply(supply: Supply) {
        val statement =
            dataSource.connection.prepareStatement("UPDATE supplies SET supply_cost = ?, supplier_id = ? WHERE supply_id = ?")
        statement.setFloat(1, supply.cost)
        statement.setLong(2, supply.supplier.id!!)
        statement.setLong(3, supply.id!!)
        statement.executeUpdate()
    }

    fun deleteSupply(id: Long) {
        val statement = dataSource.connection.prepareStatement("DELETE FROM supplies WHERE supply_id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }
}