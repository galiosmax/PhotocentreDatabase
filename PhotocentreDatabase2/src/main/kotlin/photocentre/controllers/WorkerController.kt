package photocentre.controllers

import photocentre.dataClasses.Worker
import photocentre.executors.WorkerExecutor

class WorkerController(private val executor: WorkerExecutor) {

    fun createWorker(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 3) {
            return "3 args expected"
        }

        val worker = Worker(null, args[0], args[1], args[2])
        return executor.createWorker(worker).toString()
    }

    fun createWorkers(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 3 != 0) {
            return "args mod 3 expected"
        }

        val toCreate = ArrayList<Worker>()

        for (i in 0 until args.size step 3) {
            toCreate.add(Worker(null, args[i], args[i + 1], args[i + 2]))
        }
        return executor.createWorkers(toCreate).toString()
    }

    fun getWorker(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findWorker(args[0].toLong()).toString()
    }

    fun deleteWorker(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteWorker(args[0].toLong()).toString()
    }
}