package photocentre.dataClasses

import java.sql.Date

data class Supply
(
        val id: Long?,
        val cost: Float,
        val date: Date,
        val completionDate: Date,
        val supplier: Supplier
)