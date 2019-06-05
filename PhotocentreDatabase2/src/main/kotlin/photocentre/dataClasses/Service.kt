package photocentre.dataClasses

data class Service
(
        val id: Long?,
        val name: String,
        val cost: Float,
        val branchOffice: BranchOffice
)