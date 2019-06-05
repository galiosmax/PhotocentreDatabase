package photocentre.dataClasses

import java.sql.Date

data class SoldItem
(
        val id: Long?,
        val name: String,
        val cost: Float,
        val date: Date,
        val branchOffice: BranchOffice
)