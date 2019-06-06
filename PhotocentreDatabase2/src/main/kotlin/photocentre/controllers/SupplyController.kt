package photocentre.controllers

import photocentre.dataClasses.Supply
import photocentre.executors.SupplyExecutor

class SupplyController(private val executor: SupplyExecutor) {

    fun createSupply(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }

        val supply = Supply(null, args[0].toFloat())
        return executor.createSupply(supply).toString()
    }

    fun createSupplies(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty()) {
            return "args expected"
        }

        val toCreate = ArrayList<Supply>()

        for (i in 0 until args.size) {
            toCreate.add(Supply(null, args[i].toFloat()))
        }
        return executor.createSupplies(toCreate).toString()
    }

    fun getSupply(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findSupply(args[0].toLong()).toString()
    }

    fun deleteSupply(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteSupply(args[0].toLong()).toString()
    }
}