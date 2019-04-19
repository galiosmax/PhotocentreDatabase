package photocentre

import java.sql.Statement
import javax.sql.DataSource

class BranchOfficeDao(private val dataSource: DataSource) {

    fun createBranchOffice(toCreate: BranchOffice): Long {
        val statement = dataSource.connection.prepareStatement("INSERT INTO Branch_Offices (branch_office_address, branch_office_amount_of_workers) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, toCreate.address)
        statement.setInt(2, toCreate.amountOfWorkers)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createBranchOffices(toCreate: Iterable<BranchOffice>): List<Long> {
        val statement = dataSource.connection.prepareStatement("INSERT INTO Branch_Offices (branch_office_address, branch_office_amount_of_workers) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS)

        for (branchOffice in toCreate) {
            statement.setString(1, branchOffice.address)
            statement.setInt(2, branchOffice.amountOfWorkers)
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

    fun findBranchOffice(id: Long): BranchOffice? {
        val statement = dataSource.connection.prepareStatement("SELECT branch_office_id, branch_office_address, branch_office_amount_of_workers FROM Branch_Offices WHERE branch_office_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            BranchOffice(resultSet.getLong("branch_office_id"), resultSet.getString("branch_office_address"), resultSet.getInt("branch_office_amount_of_workers"))
        } else {
            null
        }
    }

    fun updateBranchOffice(branchOffice: BranchOffice) {
        val statement = dataSource.connection.prepareStatement("UPDATE BranchOffices SET branch_office_address = ?, branch_office_amount_of_workers = ? WHERE branch_office_id = ?")
        statement.setString(1, branchOffice.address)
        statement.setInt(2, branchOffice.amountOfWorkers)
        statement.setLong(3, branchOffice.id!!)
        statement.executeUpdate()
    }
}