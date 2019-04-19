package photocentre

import java.sql.Statement
import javax.sql.DataSource

class WorkerDao(private val dataSource: DataSource) {
    
    fun createWorker(toCreate: Worker): Long {
        val statement = dataSource.connection.prepareStatement("INSERT INTO Workers (worker_name, worker_area_of_work, worker_position) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, toCreate.name)
        statement.setString(2, toCreate.areaOfWork)
        statement.setString(3, toCreate.position)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createWorkers(toCreate: Iterable<Worker>): List<Long> {
        val statement = dataSource.connection.prepareStatement("INSERT INTO Workers (worker_name, worker_area_of_work, worker_position) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)

        for (worker in toCreate) {
            statement.setString(1, worker.name)
            statement.setString(2, worker.areaOfWork)
            statement.setString(3, worker.position)
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

    fun findWorker(id: Long): Worker? {
        val statement = dataSource.connection.prepareStatement("SELECT worker_id, worker_name, worker_area_of_work, worker_position FROM Workers WHERE worker_id = ?")
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Worker(resultSet.getLong("worker_id"), resultSet.getString("worker_name"), resultSet.getString("worker_area_of_work"), resultSet.getString("worker_position"))
        } else {
            null
        }
    }

    fun updateWorker(worker: Worker) {
        val statement = dataSource.connection.prepareStatement("UPDATE Workers SET worker_name = ?, worker_area_of_work = ?, worker_position = ? WHERE worker_id = ?")
        statement.setString(1, worker.name)
        statement.setString(2, worker.areaOfWork)
        statement.setString(3, worker.position)
        statement.setLong(4, worker.id!!)
        statement.executeUpdate()
    }
}