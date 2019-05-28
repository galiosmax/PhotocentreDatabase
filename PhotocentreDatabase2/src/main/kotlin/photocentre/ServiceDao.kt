package photocentre

import java.sql.Statement
import javax.sql.DataSource

class ServiceDao(private val dataSource: DataSource) {

    fun createService(toCreate: Service): Long {
        val statement = dataSource.connection.prepareStatement(
            "INSERT INTO services (service_name, service_cost, service_date, branch_office_id) VALUES(?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        statement.setString(1, toCreate.name)
        statement.setFloat(2, toCreate.cost)
        statement.setDate(3, toCreate.date)
        statement.setLong(4, toCreate.branchOffice.id!!)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createServices(toCreate: Iterable<Service>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
            "INSERT INTO services (service_name, service_cost, service_date, branch_office_id) VALUES(?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )

        for (service in toCreate) {
            statement.setString(1, service.name)
            statement.setFloat(2, service.cost)
            statement.setDate(3, service.date)
            statement.setLong(4, service.branchOffice.id!!)
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
    fun findService(id: Long): Service? {
        val statement =
            dataSource.connection.prepareStatement("SELECT service_id, service_name, service_cost, service_date, branch_office_id FROM services WHERE service_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Service(
                resultSet.getLong("service_id"),
                resultSet.getString("service_name"),
                resultSet.getFloat("service_cost"),
                resultSet.getDate("service_date"),
                    BranchOffice(resultSet.getLong("branch_office_id"), )
            )
        } else {
            null
        }
    }

    fun updateService(service: Service) {
        val statement =
            dataSource.connection.prepareStatement("UPDATE services SET service_name = ?, service_cost = ?, service_date = ?, branch_office_id = ? WHERE service_id = ?")
        statement.setString(1, service.name)
        statement.setFloat(2, service.cost)
        statement.setDate(3, service.date)
        statement.setLong(4, service.branchOffice.id!!)
        statement.setLong(5, service.id!!)
        statement.executeUpdate()
    }

    fun deleteService(id: Long) {
        val statement = dataSource.connection.prepareStatement("DELETE FROM services WHERE service_id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }
}