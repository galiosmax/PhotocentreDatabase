package photocentre

fun main() {
    val db = Db()
    val photocentreDataSource = PhotocentreDataSource(db.dataSource)
    val photocentreDao = PhotocentreDao(photocentreDataSource)
    val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
    val controller = Controller(Executor(photocentreDataSource, photocentreDao, branchOfficeDao))
    generateSequence { print("> "); readLine() }
        .takeWhile { it != "exit" }
        .map {
            try {
                if (it.trim().startsWith("create")) {
                    when {
                        it.trim().contains("photocentre") -> controller.createPhotocentre(it.substring("create photocentre".length).trim())
                        it.trim().contains("branch offices") -> controller.createBranchOffices(it.substring("create branch offices".length).trim())
                        it.trim().contains("branch office") -> controller.createBranchOffice(it.substring("create branch office".length).trim())
                        else -> "Unknown command: $it"
                    }
                } else if (it.trim().startsWith("get")) {
                    if (it.trim().contains("branch office")) {
                        controller.getBranchOffice(it.substring("get branch office".length).trim())
                    } else {
                        "Unknown command: $it"
                    }
                } else {
                    "Unknown command: $it"
                }
            } catch (e: Exception) {
                println("Error has been occurred: $e")
                e.printStackTrace()
            }
        }.forEach {
            println(it)
        }
}