package photocentre.dataClasses

import photocentre.enums.Urgency
import photocentre.enums.OrderType
import java.sql.Date

data class Order
(
        val id: Long? = null,
        val urgent: Boolean = false,
        val cost: Float = 0f,
        val date: Date = Date(java.util.Date().time),
        val completionDate: Date? = null,
        val type: OrderType? = null,
        val branchOffice: BranchOffice? = null,
        val kiosk: Kiosk? = null,
        val customer: Customer? = null
)