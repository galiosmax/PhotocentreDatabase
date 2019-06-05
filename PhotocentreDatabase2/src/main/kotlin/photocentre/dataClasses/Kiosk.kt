package photocentre.dataClasses

data class Kiosk(
        val id: Long? = null,
        val address: String = "",
        val amountOfWorkers: Int = 0,
        val branchOffice: BranchOffice
)