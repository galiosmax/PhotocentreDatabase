package photocentre.dataClasses

import java.sql.Date

class Supply
(
        val id: Long? = null,
        val cost: Float = 0f,
        val date: Date = Date(java.util.Date().time),
        val completionDate: Date? = null,
        val supplier: Supplier? = null
)