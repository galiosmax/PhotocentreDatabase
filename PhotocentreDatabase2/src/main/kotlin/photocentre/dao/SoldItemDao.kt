package photocentre.dao

import photocentre.dataClasses.OfficeItem
import photocentre.dataClasses.SoldItem
import java.sql.Statement
import javax.sql.DataSource

class SoldItemDao(private val dataSource: DataSource) {

    fun createSoldItem(toCreate: SoldItem): Long {
        val statement = dataSource.connection.prepareStatement(
                "insert into sold_items (sold_item_name, sold_item_cost, sold_item_date, branch_office_id values(?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, toCreate.name)
        statement.setFloat(2, toCreate.cost)
        statement.setDate(3, toCreate.date)
        statement.setLong(4, toCreate.branchOffice.id!!)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createSoldItems(toCreate: Iterable<SoldItem>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
                "insert into sold_items (sold_item_name, sold_item_cost, sold_item_date, branch_office_id values(?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)

        for (item in toCreate) {
            statement.setString(1, item.name)
            statement.setFloat(2, item.cost)
            statement.setDate(3, item.date)
            statement.setLong(4, item.branchOffice.id!!)
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

    /*fun findOfficeItem(id: Long): OfficeItem? {
        val statement = dataSource.connection.prepareStatement("SELECT item_id, item_name, item_cost, item_date, item_type, branch_office_id, supply_id FROM items WHERE item_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            OfficeItem(resultSet.getLong("item_id"),
                resultSet.getString("item_name"),
                resultSet.getFloat("item_cost"),
                resultSet.getDate("item_date"),
                resultSet.getString("item_type"),
                    BranchOffice(resultSet.getLong("branch_office_id"), ))
        } else {
            null
        }
    }*/

    fun updateSoldItem(item: SoldItem) {
        val statement = dataSource.connection.prepareStatement(
                "update sold_items set sold_item_name = ?, sold_item_cost = ?, sold_item_date = ?, branch_office_id = ? where sold_item_id = ?")
        statement.setString(1, item.name)
        statement.setFloat(2, item.cost)
        statement.setDate(3, item.date)
        statement.setLong(4, item.branchOffice.id!!)
        statement.setLong(5, item.id!!)

        statement.executeUpdate()
    }

    fun deleteSoldItem(id: Long) {
        val statement = dataSource.connection.prepareStatement(
                "delete from sold_items where sold_item_id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }
}