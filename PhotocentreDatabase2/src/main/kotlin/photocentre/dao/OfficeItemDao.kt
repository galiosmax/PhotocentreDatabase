package photocentre.dao

import photocentre.dataClasses.OfficeItem
import java.sql.Statement
import javax.sql.DataSource

class OfficeItemDao(private val dataSource: DataSource) {

    fun createOfficeItem(toCreate: OfficeItem): Long {
        val statement = dataSource.connection.prepareStatement(
                "insert into office_items (office_item_purpose, office_item_amount, office_item_recommended, office_item_critical, office_item_name, office_item_cost, office_item_type, branch_office_id values(?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, toCreate.purpose.toString())
        statement.setInt(2, toCreate.amount)
        statement.setInt(3, toCreate.recommendedAmount)
        statement.setInt(4, toCreate.criticalAmount)
        statement.setString(5, toCreate.name)
        statement.setFloat(6, toCreate.cost)
        statement.setString(7, toCreate.type.toString())
        statement.setLong(8, toCreate.branchOffice.id!!)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createOfficeItems(toCreate: Iterable<OfficeItem>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
                "insert into office_items (office_item_purpose, office_item_amount, office_item_recommended, office_item_critical, office_item_name, office_item_cost, office_item_type, branch_office_id values(?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)

        for (item in toCreate) {
            statement.setString(1, item.purpose.toString())
            statement.setInt(2, item.amount)
            statement.setInt(3, item.recommendedAmount)
            statement.setInt(4, item.criticalAmount)
            statement.setString(5, item.name)
            statement.setFloat(6, item.cost)
            statement.setString(7, item.type.toString())
            statement.setLong(8, item.branchOffice.id!!)
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

    fun updateOfficeItem(item: OfficeItem) {
        val statement = dataSource.connection.prepareStatement(
                "update office_items set office_item_purpose = ?, office_item_amount = ?, office_item_recommended = ?, office_item_critical = ?, office_item_name = ?, office_item_cost = ?, office_item_type = ?, branch_office_id = ? where office_item_id = ?")
        statement.setString(1, item.purpose.toString())
        statement.setInt(2, item.amount)
        statement.setInt(3, item.recommendedAmount)
        statement.setInt(4, item.criticalAmount)
        statement.setString(5, item.name)
        statement.setFloat(6, item.cost)
        statement.setString(7, item.type.toString())
        statement.setLong(8, item.branchOffice.id!!)
        statement.setLong(9, item.id!!)

        statement.executeUpdate()
    }

    fun deleteOfficeItem(id: Long) {
        val statement = dataSource.connection.prepareStatement(
                "delete from office_items where office_item_id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }
}