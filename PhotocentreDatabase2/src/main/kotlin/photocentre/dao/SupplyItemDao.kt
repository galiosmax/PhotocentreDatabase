//package photocentre.dao
//
//import photocentre.dataClasses.OfficeItem
//import photocentre.dataClasses.SoldItem
//import photocentre.dataClasses.SupplyItem
//import java.sql.Statement
//import javax.sql.DataSource
//
//class SupplyItemDao(private val dataSource: DataSource) {
//
//    fun createSupplyItem(toCreate: SupplyItem): Long {
//        val statement = dataSource.connection.prepareStatement(
//                "insert into supply_items (supply_item_name, supply_item_amount, supply_item_type, supply_id values(?, ?, ?, ?)",
//                Statement.RETURN_GENERATED_KEYS)
//        statement.setString(1, toCreate.name)
//        statement.setInt(2, toCreate.amount)
//        statement.setString(3, toCreate.type.toString())
//        statement.setLong(4, toCreate.supply.id!!)
//        statement.executeUpdate()
//        val generated = statement.generatedKeys
//        generated.next()
//        return generated.getLong(1)
//    }
//
//    fun createSupplyItems(toCreate: Iterable<SupplyItem>): List<Long> {
//        val statement = dataSource.connection.prepareStatement(
//                "insert into supply_items (supply_item_name, supply_item_amount, supply_item_type, supply_id values(?, ?, ?, ?)",
//                Statement.RETURN_GENERATED_KEYS)
//
//        for (item in toCreate) {
//            statement.setString(1, item.name)
//            statement.setInt(2, item.amount)
//            statement.setString(3, item.type.toString())
//            statement.setLong(4, item.supply.id!!)
//            statement.addBatch()
//        }
//
//        statement.executeUpdate()
//        val generated = statement.generatedKeys
//        val res = ArrayList<Long>()
//        while(generated.next()) {
//            res += generated.getLong(1)
//        }
//        return res
//    }
//
//    /*fun findOfficeItem(id: Long): OfficeItem? {
//        val statement = dataSource.connection.prepareStatement("SELECT item_id, item_name, item_cost, item_date, item_type, branch_office_id, supply_id FROM items WHERE item_id = ?")
//        statement.setLong(1, id)
//        val resultSet = statement.executeQuery()
//        return if (resultSet.next()) {
//            OfficeItem(resultSet.getLong("item_id"),
//                resultSet.getString("item_name"),
//                resultSet.getFloat("item_cost"),
//                resultSet.getDate("item_date"),
//                resultSet.getString("item_type"),
//                    BranchOffice(resultSet.getLong("branch_office_id"), ))
//        } else {
//            null
//        }
//    }*/
//
//    fun updateSupplyItem(item: SupplyItem) {
//        val statement = dataSource.connection.prepareStatement(
//                "update supply_items set supply_item_name = ?, supply_item_amount = ?, supply_item_type = ?, supply_id = ? where supply_item_id = ?")
//        statement.setString(1, item.name)
//        statement.setInt(2, item.amount)
//        statement.setString(3, item.type.toString())
//        statement.setLong(4, item.supply.id!!)
//        statement.setLong(5, item.id!!)
//
//        statement.executeUpdate()
//    }
//
//    fun deleteSupplyItem(id: Long) {
//        val statement = dataSource.connection.prepareStatement(
//                "delete from supply_items where supply_item_id = ?")
//        statement.setLong(1, id)
//        statement.executeUpdate()
//    }
//}