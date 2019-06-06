package photocentre.controllers

import photocentre.dataClasses.Professional
import photocentre.executors.ProfessionalExecutor

class ProfessionalController(private val executor: ProfessionalExecutor) {

    fun createProfessional(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }

        val professional = Professional(null, args[0].toInt())
        return executor.createProfessional(professional).toString()
    }

    fun createProfessionals(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty()) {
            return "args expected"
        }

        val toCreate = ArrayList<Professional>()

        for (i in 0 until args.size) {
            toCreate.add(Professional(null, args[i].toInt()))
        }
        return executor.createProfessionals(toCreate).toString()
    }

    fun getProfessional(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findProfessional(args[0].toLong()).toString()
    }

    fun deleteProfessional(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteProfessional(args[0].toLong()).toString()
    }
}