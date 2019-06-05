package photocentre.dao

import photocentre.dataClasses.Order
import photocentre.enums.ItemType
import photocentre.enums.OrderType
import java.sql.Statement
import java.sql.Types.BIGINT
import javax.sql.DataSource

class OrderDao(private val dataSource: DataSource) {

    fun createOrder(order: Order): Long {
        val statement = dataSource.connection.prepareStatement(
                "insert into orders (order_urgent, order_cost, order_date, order_completion_date, order_type, branch_office_id, kiosk_id, customer_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )

        statement.setBoolean(1, order.urgent)
        statement.setFloat(2, order.cost)
        statement.setDate(3, order.date)
        statement.setDate(4, order.completionDate)
        statement.setString(5, order.type.toString())
        if (order.branchOffice != null) {
            statement.setLong(6, order.branchOffice.id!!)
        } else {
            statement.setNull(6, BIGINT)
        }
        if (order.kiosk != null) {
            statement.setLong(7, order.kiosk.id!!)
        } else {
            statement.setNull(7, BIGINT)
        }
        if (order.customer != null) {
            statement.setLong(8, order.customer.id!!)
        } else {
            statement.setNull(8, BIGINT)
        }

        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createOrders(toCreate: Iterable<Order>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
                "insert into orders (order_urgent, order_cost, order_date, order_completion_date, order_type, branch_office_id, kiosk_id, customer_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )

        for (order in toCreate) {
            statement.setBoolean(1, order.urgent)
            statement.setFloat(2, order.cost)
            statement.setDate(3, order.date)
            statement.setDate(4, order.completionDate)
            statement.setString(5, order.type.toString())
            if (order.branchOffice != null) {
                statement.setLong(6, order.branchOffice.id!!)
            } else {
                statement.setNull(6, BIGINT)
            }
            if (order.kiosk != null) {
                statement.setLong(7, order.kiosk.id!!)
            } else {
                statement.setNull(7, BIGINT)
            }
            if (order.customer != null) {
                statement.setLong(8, order.customer.id!!)
            } else {
                statement.setNull(8, BIGINT)
            }
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

    fun findOrder(id: Long): Order? {
        val statement = dataSource.connection.prepareStatement(
                "SELECT order_id, order_urgent, order_cost, order_date, order_completion_date, order_type, branch_office_id, kiosk_id, customer_id FROM orders WHERE order_id = ?"
        )

        statement.setLong(1, id)
        val resultSet = statement.executeQuery()

        val branchOfficeDao = BranchOfficeDao(dataSource)
        val kioskDao = KioskDao(dataSource)
        val customerDao = CustomerDao(dataSource)

        if (resultSet.next()) {

            val type = when (resultSet.getString("order_type")) {
                "PRINT" -> OrderType.PRINT
                "PROCESSING" -> OrderType.PROCESSING
                "BOTH" -> OrderType.BOTH
                else -> null
            }

            return Order(
                    id = resultSet.getLong("order_id"),
                    urgent = resultSet.getBoolean("order_urgent"),
                    cost = resultSet.getFloat("order_cost"),
                    date = resultSet.getDate("order_date"),
                    completionDate = resultSet.getDate("order_completion_date"),
                    type = type,
                    branchOffice = branchOfficeDao.findBranchOffice(resultSet.getLong("branch_office_id")),
                    kiosk = kioskDao.findKiosk(resultSet.getLong("kiosk_id")),
                    customer = customerDao.findCustomer(resultSet.getLong("customer_id"))
            )
        } else {
            return null
        }
    }

    fun updateOrder(order: Order) {
        val statement = dataSource.connection.prepareStatement(
                "UPDATE orders SET order_urgent = ?, order_cost = ?, order_date = ?, order_completion_date = ?, order_type = ?, branch_office_id = ?, kiosk_id = ?, customer_id = ? WHERE order_id = ?"
        )

        statement.setBoolean(1, order.urgent)
        statement.setFloat(2, order.cost)
        statement.setDate(3, order.date)
        statement.setDate(4, order.completionDate)
        statement.setString(5, order.type.toString())
        if (order.branchOffice != null) {
            statement.setLong(6, order.branchOffice.id!!)
        } else {
            statement.setNull(6, BIGINT)
        }
        if (order.kiosk != null) {
            statement.setLong(7, order.kiosk.id!!)
        } else {
            statement.setNull(7, BIGINT)
        }
        if (order.customer != null) {
            statement.setLong(8, order.customer.id!!)
        } else {
            statement.setNull(8, BIGINT)
        }
        statement.setLong(9, order.id!!)

        statement.executeUpdate()
    }

    fun deleteOrder(id: Long) {
        val statement = dataSource.connection.prepareStatement(
                "DELETE FROM orders WHERE order_id = ?"
        )
        statement.setLong(1, id)
        statement.executeUpdate()
    }



}