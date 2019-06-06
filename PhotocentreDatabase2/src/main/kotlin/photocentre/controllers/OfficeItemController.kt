package photocentre.controllers

import photocentre.dataClasses.OfficeItem
import photocentre.executors.OfficeItemExecutor

class OfficeItemController(private val executor: OfficeItemExecutor) {

    fun createItem(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 4) {
            return "4 args expected"
        }

        val item = OfficeItem(null, args[0], args[1].toFloat(), Date.valueOf(args[2]), args[3])
        return executor.createOfficeItem(item).toString()
    }

    fun createItems(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 4 != 0) {
            return "args mod 4 expected"
        }

        val toCreate = ArrayList<OfficeItem>()

        for (i in 0 until args.size step 4) {
            toCreate.add(OfficeItem(null, args[i], args[i + 1].toFloat(), Date.valueOf(args[i + 2]), args[i + 3]))
        }
        return executor.createOfficeItems(toCreate).toString()
    }

    fun getItem(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findOfficeItem(args[0].toLong()).toString()
    }

    fun deleteItem(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteOfficeItem(args[0].toLong()).toString()
    }
}