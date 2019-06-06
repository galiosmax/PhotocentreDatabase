package photocentre.dao

import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.Photo
import photocentre.dataClasses.Professional
import photocentre.enums.PaperType
import photocentre.enums.PhotoFormat
import java.sql.Statement
import java.sql.Types.BIGINT
import javax.sql.DataSource

class ProfessionalDao(private val dataSource: DataSource) {

    fun createProfessional(professional: Professional): Long {
        val statement = dataSource.connection.prepareStatement(
                "insert into professionals (professional_discount, branch_office_id) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )
        statement.setInt(1, professional.discount)
        if (professional.branchOffice != null) {
            statement.setLong(2, professional.branchOffice.id!!)
        } else {
            statement.setNull(2, BIGINT)
        }
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createProfessionals(toCreate: Iterable<Professional>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
                "insert into professionals (professional_discount, branch_office_id) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )

        for (professional in toCreate) {
            statement.setInt(1, professional.discount)
            if (professional.branchOffice != null) {
                statement.setLong(2, professional.branchOffice.id!!)
            } else {
                statement.setNull(2, BIGINT)
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

    fun findProfessional(id: Long): Professional? {
        val statement = dataSource.connection.prepareStatement(
                "select professional_id, professional_discount, branch_office_id from professionals where professional_id = ?"
        )

        statement.setLong(1, id)
        val resultSet = statement.executeQuery()

        val branchOfficeDao = BranchOfficeDao(dataSource)

        return if (resultSet.next()) {
            Professional(
                    id = resultSet.getLong("professional_id"),
                    discount = resultSet.getInt("professional_discount"),
                    branchOffice = branchOfficeDao.findBranchOffice(resultSet.getLong("branch_office_id"))
            )
        } else {
            null
        }
    }

    fun updateProfessional(professional: Professional) {
        val statement = dataSource.connection.prepareStatement(
                "update professionals set professional_discount = ?, branch_office_id = ? where professional_id = ?"
        )

        statement.setInt(1, professional.discount)
        if (professional.branchOffice != null) {
            statement.setLong(2, professional.branchOffice.id!!)
        } else {
            statement.setNull(2, BIGINT)
        }
        statement.setLong(3, professional.id!!)

        statement.executeUpdate()
    }

    fun deleteProfessional(id: Long) {
        val statement = dataSource.connection.prepareStatement(
                "delete from professionals where professional_id = ?"
        )

        statement.setLong(1, id)
        statement.executeUpdate()
    }

    fun gelAll(): List<Professional> {
        val statement = dataSource.connection.prepareStatement(
                "select * from professionals"
        )
        val resultSet = statement.executeQuery()
        val res = ArrayList<Professional>()

        val branchOfficeDao = BranchOfficeDao(dataSource)

        while (resultSet.next()) {

            res += Professional(
                    id = resultSet.getLong("professional_id"),
                    discount = resultSet.getInt("professional_discount"),
                    branchOffice = branchOfficeDao.findBranchOffice(resultSet.getLong("branch_office_id"))
            )
        }
        return res
    }
}