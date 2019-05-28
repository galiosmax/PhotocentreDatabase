package photocentre

import java.sql.Statement
import javax.sql.DataSource

class CustomerDao(private val dataSource: DataSource) {

    fun createCustomer(toCreate: Customer): Long {
        val statement = dataSource.connection.prepareStatement("INSERT INTO customers (customer_name, professional_id, amateur_id) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, toCreate.name)
        statement.setLong(3, toCreate.professional?.id!!)
        statement.setLong(3, toCreate.amateur?.id!!)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createCustomers(toCreate: Iterable<Customer>): List<Long> {
        val statement = dataSource.connection.prepareStatement("INSERT INTO customers (customer_name, professional_id, amateur_id) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)

        for (customer in toCreate) {
            statement.setString(1, customer.name)
            statement.setLong(3, customer.professional?.id!!)
            statement.setLong(3, customer.amateur?.id!!)
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

    //TODO REWRITE LAZY LOADING
    fun findCustomer(id: Long): Customer? {
        val statement = dataSource.connection.prepareStatement("SELECT customer_id, customer_name, professional_id, amateur_id FROM customers WHERE customer_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            //if (resultSet.getLong("professional_id") != null) {
            //}
            Customer(resultSet.getLong("customer_id"),
                    resultSet.getString("customer_name"),
                    Professional(resultSet.getLong("professional_id"), ),
                    null)
        } else {
            null
        }
    }

    fun updateCustomer(customer: Customer) {
        val statement = dataSource.connection.prepareStatement("UPDATE customers SET customer_name = ?, professional_id = ?, amateur_id = ? WHERE customer_id = ?")
        statement.setString(1, customer.name)
        statement.setLong(3, customer.professional?.id!!)
        statement.setLong(3, customer.amateur?.id!!)
        statement.setLong(7, customer.id!!)

        statement.executeUpdate()
    }

    fun deleteCustomer(id: Long) {
        val statement = dataSource.connection.prepareStatement("DELETE FROM customers WHERE customer_id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }
}