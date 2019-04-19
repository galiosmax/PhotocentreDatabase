package photocentre

import org.flywaydb.core.Flyway
import org.postgresql.ds.PGSimpleDataSource

class Db {

    val dataSource = PGSimpleDataSource().apply {
        serverName = "localhost"
        portNumber = 5432
        databaseName = "postgres"
        user = "postgres"
        password = "123"
    }

    init {

        val flyway = Flyway
            .configure()
            .dataSource(dataSource)
            .load()

        // Start the migration
        flyway.migrate()
    }
}