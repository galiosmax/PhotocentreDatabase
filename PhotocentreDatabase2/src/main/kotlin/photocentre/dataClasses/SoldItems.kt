package photocentre.dataClasses

import java.sql.Date

data class SoldItem
(
        val id: Long?,
        val name: String = "",
        val cost: Float = 0f,
        val date: Date = Date(java.util.Date().time),
        val branchOffice: BranchOffice
)