package photocentre.controllers

import photocentre.dataClasses.Service
import photocentre.executors.ServiceExecutor

class ServiceController(private val executor: ServiceExecutor) {

    fun createService(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 3) {
            return "3 args expected"
        }

        val service = Service(null, args[0], args[1].toFloat(), Date.valueOf(args[2]))
        return executor.createService(service).toString()
    }

    fun createServices(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 3 != 0) {
            return "args mod 3 expected"
        }

        val toCreate = ArrayList<Service>()

        for (i in 0 until args.size step 3) {
            toCreate.add(Service(null, args[i], args[i + 1].toFloat(), Date.valueOf(args[i + 2])))
        }
        return executor.createServices(toCreate).toString()
    }

    fun getService(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findService(args[0].toLong()).toString()
    }

    fun deleteService(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteService(args[0].toLong()).toString()
    }
}