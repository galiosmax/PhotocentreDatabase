package photocentre

import photocentre.Professional
import java.sql.Statement
import javax.sql.DataSource

class ProfessionalDao(private val dataSource: DataSource) {

    fun createProfessional(toCreate: Professional): Long {
        val statement = dataSource.connection.prepareStatement(
            "INSERT INTO professionals (professional_discount, branch_office_id) VALUES(?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        statement.setInt(1, toCreate.discount)
        statement.setLong(2, toCreate.branchOffice.id!!)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createProfessionals(toCreate: Iterable<Professional>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
            "INSERT INTO professionals (professional_discount) VALUES(?)",
            Statement.RETURN_GENERATED_KEYS
        )

        for (professional in toCreate) {
            statement.setInt(1, professional.discount)
            statement.setLong(2, professional.branchOffice.id!!)
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
    fun findProfessional(id: Long): Professional? {
        val statement =
            dataSource.connection.prepareStatement("SELECT professional_id, professional_discount, branch_office_id FROM professionals WHERE professional_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Professional(
                resultSet.getLong("professional_id"),
                resultSet.getInt("professional_discount"),
                    BranchOffice(resultSet.getLong("branch_office_id"), )
            )
        } else {
            null
        }
    }

    fun updateProfessional(professional: Professional) {
        val statement =
            dataSource.connection.prepareStatement("UPDATE professionals SET professional_discount = ?, branch_office_id = ? WHERE professional_id = ?")
        statement.setInt(1, professional.discount)
        statement.setLong(2, professional.branchOffice.id!!)
        statement.setLong(3, professional.id!!)
        statement.executeUpdate()
    }

    fun deleteProfessional(id: Long) {
        val statement = dataSource.connection.prepareStatement("DELETE FROM professionals WHERE professional_id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }
}