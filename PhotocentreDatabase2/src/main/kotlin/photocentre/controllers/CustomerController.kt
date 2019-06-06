package photocentre.controllers

import photocentre.dataClasses.Customer
import photocentre.executors.CustomerExecutor

class CustomerController(private val executor: CustomerExecutor) {

    fun createCustomer(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }

        val customer = Customer(null, args[0].toInt())
        return executor.createCustomer(customer).toString()
    }

    fun createCustomers(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty()) {
            return "args expected"
        }

        val toCreate = ArrayList<Customer>()

        for (i in 0 until args.size) {
            toCreate.add(Customer(null, args[i].toInt()))
        }
        return executor.createCustomers(toCreate).toString()
    }

    fun getCustomer(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findCustomer(args[0].toLong()).toString()
    }

    fun deleteCustomer(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteCustomer(args[0].toLong()).toString()
    }
}