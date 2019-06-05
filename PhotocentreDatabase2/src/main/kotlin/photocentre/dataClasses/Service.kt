package photocentre.dataClasses

data class Service
(
        val id: Long?,
        val name: String = "",
        val cost: Float = 0f,
        val branchOffice: BranchOffice
)