package photocentre.dao

import java.sql.Statement
import javax.sql.DataSource

class ItemDao(private val dataSource: DataSource) {

    fun createItem(toCreate: Item): Long {
        val statement = dataSource.connection.prepareStatement("INSERT INTO items (item_name, item_cost, item_date, item_type, branch_office_id, supply_id) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, toCreate.name)
        statement.setFloat(2, toCreate.cost)
        statement.setDate(3, toCreate.date)
        statement.setString(4, toCreate.type)
        statement.setLong(5, toCreate.branchOffice.id!!)
        statement.setLong(6, toCreate.supply.id!!)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createItems(toCreate: Iterable<Item>): List<Long> {
        val statement = dataSource.connection.prepareStatement("INSERT INTO items (item_name, item_cost, item_date, item_type, branch_office_id, supply_id) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)

        for (item in toCreate) {
            statement.setString(1, item.name)
            statement.setFloat(2, item.cost)
            statement.setDate(3, item.date)
            statement.setString(4, item.type)
            statement.setLong(5, item.branchOffice.id!!)
            statement.setLong(6, item.supply.id!!)
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

    /*fun findItem(id: Long): Item? {
        val statement = dataSource.connection.prepareStatement("SELECT item_id, item_name, item_cost, item_date, item_type, branch_office_id, supply_id FROM items WHERE item_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Item(resultSet.getLong("item_id"),
                resultSet.getString("item_name"),
                resultSet.getFloat("item_cost"),
                resultSet.getDate("item_date"),
                resultSet.getString("item_type"),
                    BranchOffice(resultSet.getLong("branch_office_id"), ))
        } else {
            null
        }
    }*/

    fun updateItem(item: Item) {
        val statement = dataSource.connection.prepareStatement("UPDATE items SET item_name = ?, item_cost = ?, item_date = ?, item_type = ?, branch_office_id = ?, supply_id = ? WHERE item_id = ?")
        statement.setString(1, item.name)
        statement.setFloat(2, item.cost)
        statement.setDate(3, item.date)
        statement.setString(4, item.type)
        statement.setLong(5, item.branchOffice.id!!)
        statement.setLong(6, item.supply.id!!)
        statement.setLong(7, item.id!!)

        statement.executeUpdate()
    }

    fun deleteItem(id: Long) {
        val statement = dataSource.connection.prepareStatement("DELETE FROM items WHERE item_id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }
}