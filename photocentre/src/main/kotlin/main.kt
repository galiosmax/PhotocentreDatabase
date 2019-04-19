import org.postgresql.Driver
import org.flywaydb.core.Flyway
import java.sql.*

fun main() {
    DriverManager.registerDriver(Driver())
    val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres",
        "postgres",
        "123"
    )

    val flyway = Flyway
        .configure()
        .dataSource(
            "jdbc:postgresql://localhost:5432/postgres", "postgres",
            "123"
        )
        .load()

    // Start the migration
    flyway.migrate()

}