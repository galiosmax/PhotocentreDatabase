package photocentre.controllers

import photocentre.dataClasses.Supplier
import photocentre.executors.SupplierExecutor

class SupplierController(private val executor: SupplierExecutor) {

    fun createSupplier(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 2) {
            return "2 args expected"
        }

        val supplier = Supplier(null, args[0], args[1])
        return executor.createSupplier(supplier).toString()
    }

    fun createSuppliers(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 2 != 0) {
            return "args mod 2 expected"
        }

        val toCreate = ArrayList<Supplier>()

        for (i in 0 until args.size step 2) {
            toCreate.add(Supplier(null, args[i], args[i + 1]))
        }
        return executor.createSuppliers(toCreate).toString()
    }

    fun getSupplier(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findSupplier(args[0].toLong()).toString()
    }

    fun deleteSupplier(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteSupplier(args[0].toLong()).toString()
    }
}