package photocentre

import java.sql.Statement
import javax.sql.DataSource

class OrderDao(private val dataSource: DataSource) {

    fun createOrder(toCreate: Order): Long {
        val statement = dataSource.connection.prepareStatement("INSERT INTO orders (order_urgency, order_amount_of_photos, order_discount, order_cost, order_date, order_completion_date, order_type, branch_office_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, toCreate.urgency)
        statement.setInt(2, toCreate.amountOfPhotos)
        statement.setInt(3, toCreate.discount!!)
        statement.setFloat(4, toCreate.cost)
        statement.setDate(5, toCreate.date)
        statement.setDate(6, toCreate.completionDate)
        statement.setString(7, toCreate.type)
        statement.setLong(8, toCreate.branchOffice.id!!)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createOrders(toCreate: Iterable<Order>): List<Long> {
        val statement = dataSource.connection.prepareStatement("INSERT INTO orders (order_urgency, order_amount_of_photos, order_discount, order_cost, order_date, order_completion_date, order_type, branch_office_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)

        for (order in toCreate) {
            statement.setString(1, order.urgency)
            statement.setInt(2, order.amountOfPhotos)
            statement.setInt(3, order.discount!!)
            statement.setFloat(4, order.cost)
            statement.setDate(5, order.date)
            statement.setDate(6, order.completionDate)
            statement.setString(7, order.type)
            statement.setLong(8, order.branchOffice.id!!)
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
    fun findOrder(id: Long): Order? {
        val statement = dataSource.connection.prepareStatement("SELECT order_id, order_urgency, order_amount_of_photos, order_discount, order_cost, order_date, order_completion_date, order_type, branch_office_id FROM orders WHERE order_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Order(resultSet.getLong("order_id"),
                resultSet.getString("order_urgency"),
                resultSet.getInt("order_amount_of_photos"),
                resultSet.getInt("order_discount"),
                resultSet.getFloat("order_cost"),
                resultSet.getDate("order_date"),
                resultSet.getDate("order_completion_date"),
                resultSet.getString("order_type"),
                BranchOffice(resultSet.getLong("branch_office_id"), ))
        } else {
            null
        }
    }

    fun updateOrder(order: Order) {
        val statement = dataSource.connection.prepareStatement("UPDATE orders SET order_urgency = ?, order_amount_of_photos = ?, order_discount = ?, order_cost = ?, order_date = ?, order_completion_date = ?, order_type = ?, branch_office_id = ? WHERE order_id = ?")
        statement.setString(1, order.urgency)
        statement.setInt(2, order.amountOfPhotos)
        statement.setInt(3, order.discount!!)
        statement.setFloat(4, order.cost)
        statement.setDate(5, order.date)
        statement.setDate(6, order.completionDate)
        statement.setString(7, order.type)
        statement.setLong(8, order.branchOffice.id!!)
        statement.setLong(9, order.id!!)
        statement.executeUpdate()
    }

    fun deleteOrder(id: Long) {
        val statement = dataSource.connection.prepareStatement("DELETE FROM orders WHERE order_id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }
}