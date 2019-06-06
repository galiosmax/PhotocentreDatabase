package photocentre.controllers

import photocentre.dataClasses.Amateur
import photocentre.executors.AmateurExecutor

class AmateurController(private val executor: AmateurExecutor) {

    fun createAmateur(amateur: Amateur): Amateur {

        val id = executor.createAmateur(amateur)
        return Amateur(
                id = id,
                experience = amateur.experience
        )
    }

    fun createAmateurs(amateurs: List<Amateur>): List<Amateur> {

        val ids = executor.createAmateurs(amateurs)
        val newAmateurs = ArrayList<Amateur>()

        for (i in 1..ids.size) {
            newAmateurs += Amateur(
                    id = ids[i],
                    experience = amateurs[i].experience
            )
        }
        return newAmateurs
    }

    fun getAmateur(id: Long): Amateur? {
        return executor.findAmateur(id)
    }

    fun updateAmateur(amateur: Amateur): String {
        return executor.updateAmateur(amateur).toString()
    }

    fun deleteAmateur(id: Long): String {
        return executor.deleteAmateur(id).toString()
    }

    fun getAllAmateurs():List<Amateur> {
        return executor.getAllAmateurs()
    }
}