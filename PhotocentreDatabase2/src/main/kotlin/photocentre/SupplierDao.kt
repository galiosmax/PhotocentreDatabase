package photocentre

import java.sql.Statement
import javax.sql.DataSource

class SupplierDao(private val dataSource: DataSource) {

    fun createSupplier(toCreate: Supplier): Long {
        val statement = dataSource.connection.prepareStatement(
            "INSERT INTO suppliers (supplier_name, supplier_specialization) VALUES(?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        statement.setString(1, toCreate.name)
        statement.setString(2, toCreate.specialization)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createSuppliers(toCreate: Iterable<Supplier>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
            "INSERT INTO suppliers (supplier_name, supplier_specialization) VALUES(?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )

        for (supplier in toCreate) {
            statement.setString(1, supplier.name)
            statement.setString(2, supplier.specialization)
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

    fun findSupplier(id: Long): Supplier? {
        val statement =
            dataSource.connection.prepareStatement("SELECT supplier_id, supplier_name, supplier_specialization FROM suppliers WHERE supplier_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Supplier(
                resultSet.getLong("supplier_id"),
                resultSet.getString("supplier_name"),
                resultSet.getString("supplier_specialization")
            )
        } else {
            null
        }
    }

    fun updateSupplier(supplier: Supplier) {
        val statement =
            dataSource.connection.prepareStatement("UPDATE suppliers SET supplier_name = ?, supplier_specialization = ? WHERE supplier_id = ?")
        statement.setString(1, supplier.name)
        statement.setString(2, supplier.specialization)
        statement.setLong(3, supplier.id!!)
        statement.executeUpdate()
    }

    fun deleteSupplier(id: Long) {
        val statement = dataSource.connection.prepareStatement("DELETE FROM suppliers WHERE supplier_id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }
}