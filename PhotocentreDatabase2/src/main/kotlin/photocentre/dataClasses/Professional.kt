package photocentre.dataClasses

data class Professional
(
        val id: Long?,
        val discount: Int = 0,
        val branchOffice: BranchOffice
)