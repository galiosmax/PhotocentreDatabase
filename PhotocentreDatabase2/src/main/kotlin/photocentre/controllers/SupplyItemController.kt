package photocentre.controllers

import photocentre.dataClasses.SupplyItem
import photocentre.executors.SupplyItemExecutor
import java.sql.Date

class SupplyItemController(private val executor: SupplyItemExecutor) {

    fun createItem(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 4) {
            return "4 args expected"
        }

        val item = SupplyItem(null, args[0], args[1].toFloat(), Date.valueOf(args[2]), args[3])
        return executor.createSupplyItem(item).toString()
    }

    fun createItems(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 4 != 0) {
            return "args mod 4 expected"
        }

        val toCreate = ArrayList<SupplyItem>()

        for (i in 0 until args.size step 4) {
            toCreate.add(SupplyItem(null, args[i], args[i + 1].toFloat(), Date.valueOf(args[i + 2]), args[i + 3]))
        }
        return executor.createSupplyItems(toCreate).toString()
    }

    fun getItem(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findSupplyItem(args[0].toLong()).toString()
    }

    fun deleteItem(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteSupplyItem(args[0].toLong()).toString()
    }
}