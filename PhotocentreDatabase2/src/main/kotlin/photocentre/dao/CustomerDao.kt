package photocentre.dao

import com.sun.org.apache.xml.internal.dtm.ref.CustomStringPool
import photocentre.dataClasses.Amateur
import photocentre.dataClasses.BranchOffice
import photocentre.dataClasses.Customer
import photocentre.dataClasses.Professional
import java.sql.Statement
import java.sql.Types.BIGINT
import javax.sql.DataSource

class CustomerDao(private val dataSource: DataSource) {

    fun createCustomer(customer: Customer): Long {
        val statement = dataSource.connection.prepareStatement(
                "insert into customers (customer_name, customer_discount, professional_id, amateur_id) values (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )

        statement.setString(1, customer.name)
        statement.setInt(2, customer.discount)
        if (customer.professional != null) {
            statement.setLong(3, customer.professional.id!!)
        } else {
            statement.setNull(3, BIGINT)
        }
        if (customer.amateur != null) {
            statement.setLong(4, customer.amateur.id!!)
        } else {
            statement.setNull(4, BIGINT)
        }

        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createCustomers(toCreate: Iterable<Customer>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
                "insert into customers (customer_name, customer_discount, professional_id, amateur_id) values (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )

        for (customer in toCreate) {
            statement.setString(1, customer.name)
            statement.setInt(2, customer.discount)
            if (customer.professional != null) {
                statement.setLong(3, customer.professional.id!!)
            } else {
                statement.setNull(3, BIGINT)
            }
            if (customer.amateur != null) {
                statement.setLong(4, customer.amateur.id!!)
            } else {
                statement.setNull(4, BIGINT)
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

    fun findCustomer(id: Long?): Customer? {
        val statement = dataSource.connection.prepareStatement(
                "select customer_id, customer_name, customer_discount, professional_id, amateur_id from customers where customer_id = ?"
        )
        if (id != null) {
            statement.setLong(1, id)
        } else {
            return null
        }
        val resultSet = statement.executeQuery()
        val professionalDao = ProfessionalDao(dataSource)
        val amateurDao = AmateurDao(dataSource)

        return if (resultSet.next()) {
            Customer(
                    id = resultSet.getLong("customer_id"),
                    name = resultSet.getString("customer_name"),
                    discount = resultSet.getInt("customer_discount"),
                    professional = professionalDao.findProfessional(resultSet.getLong("professional_id")),
                    amateur = amateurDao.findAmateur(resultSet.getLong("amateur_id"))
            )
        } else {
            null
        }
    }

    fun updateCustomer(customer: Customer) {
        val statement = dataSource.connection.prepareStatement(
                "update customers set customer_name = ?, customer_discount = ?, professional_id = ?, amateur_id = ? where customer_id = ?"
        )
        statement.setString(1, customer.name)
        statement.setInt(2, customer.discount)
        if (customer.professional != null) {
            statement.setLong(3, customer.professional.id!!)
        } else {
            statement.setNull(3, BIGINT)
        }
        if (customer.amateur != null) {
            statement.setLong(4, customer.amateur.id!!)
        } else {
            statement.setNull(4, BIGINT)
        }
        statement.setLong(5, customer.id!!)

        statement.executeUpdate()
    }

    fun deleteCustomer(id: Long) {
        val statement = dataSource.connection.prepareStatement(
                "delete from customers where customer_id = ?"
        )
        statement.setLong(1, id)
        statement.executeUpdate()
    }

    fun getByOffice(branchOffice: BranchOffice): List<Customer> {
        val statement = dataSource.connection.prepareStatement(
                "select customer_name, " +
                        "branch_offices.branch_office_id as office_id, " +
                        "branch_offices.branch_office_address as address " +
                        "professional_id " +
                        "from customers " +
                        "join professionals " +
                        "on customers.professional_id = professionals.professional_id " +
                        "join branch_offices " +
                        "on professionals.branch_office_id = ? " +
                        "where professional_id is not null"
        )
        statement.setLong(1, branchOffice.id!!)
        val resultSet = statement.executeQuery()
        val res = ArrayList<Customer>()

        while (resultSet.next()) {
            res += Customer(
                    id = resultSet.getLong("customer_id"),
                    name = resultSet.getString("customer_name"),
                    professional = Professional(
                            id = resultSet.getLong("professional_id"),
                            branchOffice = BranchOffice(
                                    id = resultSet.getLong("office_id"),
                                    address = resultSet.getString("address")
                            )
                    )
            )
        }
        return res
    }

    //TODO
    fun getIfDiscount(): List<Customer> {
        val statement = dataSource.connection.prepareStatement(
                "select customer_name, " +
                        "(case when professional_id is not null then professionals.professional_discount else customer_discount end) as customer_discount " +
                        "from customers " +
                        "join professionals " +
                        "where customer_discount > 0 or professional_id is not null " +
                        "order by customer_discount desc"
        )
        val resultSet = statement.executeQuery()
        val res = ArrayList<Customer>()

        while (resultSet.next()) {
            res += Customer(
                    name = resultSet.getString("customer_name"),
                    discount = resultSet.getInt("customer_discount")
            )
        }
        return res
    }


    fun getByVolume(volume: Int): List<Pair<Customer, Int>> {
        val statement = dataSource.connection.prepareStatement(
                "select customers.customer_name, count(photo_id) as photo_amount " +
                        "from orders " +
                        "left join customers " +
                        "on orders.customer_id = customers.customer_id " +
                        "join films " +
                        "on films.order_id = orders.order_id " +
                        "left join photos " +
                        "on photos.film_id = films.film_id " +
                        "group by customers.customer_name " +
                        "having count(photo_id) >= ? " +
                        "order by photo_amount desc"
        )
        statement.setInt(1, volume)
        val resultSet = statement.executeQuery()
        val res = ArrayList<Pair<Customer, Int>>()

        while (resultSet.next()) {
            res += Pair(
                    Customer(
                            name = resultSet.getString("customer_name")
                    ),
                    resultSet.getInt("photo_amount")
            )
        }
        return res
    }
}