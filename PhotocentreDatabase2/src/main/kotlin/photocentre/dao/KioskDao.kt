package photocentre.dao

import photocentre.dataClasses.Kiosk
import java.sql.Statement
import javax.sql.DataSource

class KioskDao(private val dataSource: DataSource) {

    fun createKiosk(toCreate: Kiosk): Long {
        val statement = dataSource.connection.prepareStatement("INSERT INTO kiosks (kiosk_address, kiosk_amount_of_workers, branch_office_id) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, toCreate.address)
        statement.setInt(2, toCreate.amountOfWorkers)
        statement.setLong(3, toCreate.branchOffice.id!!)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createKiosks(toCreate: Iterable<Kiosk>): List<Long> {
        val statement = dataSource.connection.prepareStatement("INSERT INTO kiosks (kiosk_address, kiosk_amount_of_workers, branch_office_id) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)

        for (kiosk in toCreate) {
            statement.setString(1, kiosk.address)
            statement.setInt(2, kiosk.amountOfWorkers)
            statement.setLong(3, kiosk.branchOffice.id!!)
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
    /*fun findKiosk(id: Long): Kiosk? {
        val statement = dataSource.connection.prepareStatement("SELECT kiosk_id, kiosk_address, kiosk_amount_of_workers, branch_office_id FROM kiosks WHERE kiosk_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Kiosk(resultSet.getLong("kiosk_id"), resultSet.getString("kiosk_address"), resultSet.getInt("kiosk_amount_of_workers"), BranchOffice(resultSet.getLong("branch_office_id"), ))
        } else {
            null
        }
    }*/

    fun updateKiosk(kiosk: Kiosk) {
        val statement = dataSource.connection.prepareStatement("UPDATE kiosks SET kiosk_address = ?, kiosk_amount_of_workers = ?, branch_office_id = ? WHERE kiosk_id = ?")
        statement.setString(1, kiosk.address)
        statement.setInt(2, kiosk.amountOfWorkers)
        statement.setLong(3, kiosk.branchOffice.id!!)
        statement.setLong(4, kiosk.id!!)
        statement.executeUpdate()
    }

    fun deleteKiosk(id: Long) {
        val statement = dataSource.connection.prepareStatement("DELETE FROM kiosks WHERE kiosk_id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }
}