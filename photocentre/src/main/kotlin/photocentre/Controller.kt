package photocentre

class Controller(private val executor: Executor) {

    fun createPhotocentre(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }

        val photocentre = Photocentre(null, args[0])
        return executor.createPhotocentre(photocentre).toString()
    }

    fun createBranchOffice(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 2) {
            return "2 args expected"
        }

        val branchOffice = BranchOffice(null, args[0], args[1].toInt())
        return executor.createBranchOffice(branchOffice).toString()
    }

    fun createBranchOffices(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 2 != 0) {
            return "even args expected"
        }

        val toCreate = ArrayList<BranchOffice>()

        for (i in 0 until args.size step 2) {
            toCreate.add(BranchOffice(null, args[i], args[i + 1].toInt()))
        }
        return executor.createBranchOffices(toCreate).toString()
    }

    fun getBranchOffice(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "2 args expected"
        }
        return executor.findBranchOffice(args[0].toLong()).toString()
    }

}