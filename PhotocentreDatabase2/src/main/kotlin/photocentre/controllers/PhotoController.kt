package photocentre.controllers

import photocentre.dataClasses.Photo
import photocentre.executors.PhotoExecutor

class PhotoController(private val executor: PhotoExecutor) {

    fun createPhoto(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 3) {
            return "3 args expected"
        }

        val photo = Photo(null, args[0], args[1], args[2])
        return executor.createPhoto(photo).toString()
    }

    fun createPhotos(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 3 != 0) {
            return "args mod 3 expected"
        }

        val toCreate = ArrayList<Photo>()

        for (i in 0 until args.size step 3) {
            toCreate.add(Photo(null, args[i], args[i + 1], args[i + 2]))
        }
        return executor.createPhotos(toCreate).toString()
    }

    fun getPhoto(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findPhoto(args[0].toLong()).toString()
    }

    fun deletePhoto(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deletePhoto(args[0].toLong()).toString()
    }
}