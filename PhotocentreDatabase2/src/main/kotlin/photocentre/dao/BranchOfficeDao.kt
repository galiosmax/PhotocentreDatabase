package photocentre.dao

import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.Kiosk
import java.sql.Statement
import javax.sql.DataSource

class BranchOfficeDao(private val dataSource: DataSource) {

    fun createBranchOffice(branchOffice: BranchOffice): Long {
        val statement = dataSource.connection.prepareStatement(
                "insert into branch_offices (branch_office_address, branch_office_amount_of_workers) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )
        statement.setString(1, branchOffice.address)
        statement.setInt(2, branchOffice.amountOfWorkers!!)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createBranchOffices(toCreate: Iterable<BranchOffice>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
                "insert into branch_offices (branch_office_address, branch_office_amount_of_workers) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )

        for (branchOffice in toCreate) {
            statement.setString(1, branchOffice.address)
            statement.setInt(2, branchOffice.amountOfWorkers!!)
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

    fun findBranchOffice(id: Long): BranchOffice? {
        val statement = dataSource.connection.prepareStatement(
                "select branch_office_id, branch_office_address, branch_office_amount_of_workers from branch_offices where branch_office_id = ?"
        )
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            BranchOffice(
                    id = resultSet.getLong("branch_office_id"),
                    address = resultSet.getString("branch_office_address"),
                    amountOfWorkers = resultSet.getInt("branch_office_amount_of_workers")
            )
        } else {
            null
        }
    }

    fun updateBranchOffice(branchOffice: BranchOffice) {
        val statement = dataSource.connection.prepareStatement(
                "update branch_offices set branch_office_address = ?, branch_office_amount_of_workers = ? where branch_office_id = ?"
        )
        statement.setString(1, branchOffice.address)
        statement.setInt(2, branchOffice.amountOfWorkers!!)
        statement.setLong(3, branchOffice.id!!)
        statement.executeUpdate()
    }

    fun deleteBranchOffice(id: Long) {
        val statement = dataSource.connection.prepareStatement(
                "delete from branch_offices where branch_office_id = ?"
        )
        statement.setLong(1, id)
        statement.executeUpdate()
    }

    fun countBranchOffices(): Int {
        val statement = dataSource.connection.prepareStatement(
                "select count(*) as Total_Offices from branch_offices"
        )
        val resultSet = statement.executeQuery()
        return resultSet.getInt("Total_Offices")
    }

    fun selectBranchOffices(): List<BranchOffice> {
        val statement = dataSource.connection.prepareStatement(
                "select branch_office_id, branch_office_address from branch_offices"
        )
        val resultSet = statement.executeQuery()
        val res = ArrayList<BranchOffice>()

        while (resultSet.next()) {
            res += BranchOffice(
                    id = resultSet.getLong("branch_office_id"),
                    address = resultSet.getString("branch_office_address")
            )
        }
        return res
    }

    fun selectBranchOfficesAndKiosks(): List<Pair<BranchOffice, Kiosk>> {
        val statement = dataSource.connection.prepareStatement(
                "select branch_offices.branch_office_id as office_id," +
                        "branch_offices.branch_office_address as office_address," +
                        "kiosks.kiosk_id as kiosk_id," +
                        "kiosks.kiosk_address as kiosk_address" +
                        "from branch_offices" +
                        "left join kiosks" +
                        "on branch_offices.branch_office_id = kiosks.branch_office_id" +
                        "order by office_id, kiosk_id;"
        )
        val resultSet = statement.executeQuery()
        val res = ArrayList<Pair<BranchOffice, Kiosk>>()

        while (resultSet.next()) {

            val office = BranchOffice(
                    id = resultSet.getLong("office_id"),
                    address = resultSet.getString("office_address")
            )

            res += Pair(
                    office,
                    Kiosk(
                            id = resultSet.getLong("kiosk_id"),
                            address = resultSet.getString("kiosk_address"),
                            branchOffice = office
                    )
            )
        }
        return res
    }
}