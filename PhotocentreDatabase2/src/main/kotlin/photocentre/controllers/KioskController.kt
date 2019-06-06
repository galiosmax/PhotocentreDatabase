package photocentre.controllers

import photocentre.dataClasses.Kiosk
import photocentre.executors.KioskExecutor

class KioskController(private val executor: KioskExecutor) {

    fun createKiosk(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 2) {
            return "2 args expected"
        }

        val kiosk = Kiosk(null, args[0], args[1].toInt())
        return executor.createKiosk(kiosk).toString()
    }

    fun createKiosks(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 2 != 0) {
            return "even args expected"
        }

        val toCreate = ArrayList<Kiosk>()

        for (i in 0 until args.size step 2) {
            toCreate.add(Kiosk(null, args[i], args[i + 1].toInt()))
        }
        return executor.createKiosks(toCreate).toString()
    }

    fun getKiosk(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findKiosk(args[0].toLong()).toString()
    }

    fun deleteKiosk(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteKiosk(args[0].toLong()).toString()
    }
}