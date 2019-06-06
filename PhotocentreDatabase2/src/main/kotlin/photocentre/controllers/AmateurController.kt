package photocentre.controllers

import photocentre.dataClasses.Amateur
import photocentre.executors.AmateurExecutor

class AmateurController(private val executor: AmateurExecutor) {

    fun createAmateur(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }

        val amateur = Amateur(null, args[0].toInt())
        return executor.createAmateur(amateur).toString()
    }

    fun createAmateurs(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty()) {
            return "args expected"
        }

        val toCreate = ArrayList<Amateur>()

        for (i in 0 until args.size) {
            toCreate.add(Amateur(null, args[i].toInt()))
        }
        return executor.createAmateurs(toCreate).toString()
    }

    fun getAmateur(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findAmateur(args[0].toLong()).toString()
    }

    fun deleteAmateur(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteAmateur(args[0].toLong()).toString()
    }
}