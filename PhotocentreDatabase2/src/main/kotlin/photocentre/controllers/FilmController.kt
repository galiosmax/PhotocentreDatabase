package photocentre.controllers

import photocentre.dataClasses.Film
import photocentre.executors.FilmExecutor

class FilmController(private val executor: FilmExecutor) {

    fun createFilm(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }

        val film = Film(null, args[0].toInt())
        return executor.createFilm(film).toString()
    }

    fun createFilms(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty()) {
            return "args expected"
        }

        val toCreate = ArrayList<Film>()

        for (i in 0 until args.size) {
            toCreate.add(Film(null, args[i].toInt()))
        }
        return executor.createFilms(toCreate).toString()
    }

    fun getFilm(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findFilm(args[0].toLong()).toString()
    }

    fun deleteFilm(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteFilm(args[0].toLong()).toString()
    }
}