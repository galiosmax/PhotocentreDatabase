package photocentre.controllers

import photocentre.dataClasses.Order
import photocentre.executors.OrderExecutor

class OrderController(private val executor: OrderExecutor) {

    fun createOrder(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 7) {
            return "7 args expected"
        }

        val order = Order(null, args[0], args[1].toInt(), args[2].toInt(), args[3].toFloat(), Date.valueOf(args[4]), Date.valueOf(args[5]), args[6])
        return executor.createOrder(order).toString()
    }

    fun createOrders(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 7 != 0) {
            return "args mod 7 expected"
        }

        val toCreate = ArrayList<Order>()

        for (i in 0 until args.size step 7) {
            toCreate.add(Order(null, args[i], args[i + 1].toInt(), args[i + 2].toInt(), args[i + 3].toFloat(), Date.valueOf(args[i + 4]), Date.valueOf(args[i + 5]), args[i + 6]))
        }
        return executor.createOrders(toCreate).toString()
    }

    fun getOrder(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findOrder(args[0].toLong()).toString()
    }

    fun deleteOrder(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteOrder(args[0].toLong()).toString()
    }
}