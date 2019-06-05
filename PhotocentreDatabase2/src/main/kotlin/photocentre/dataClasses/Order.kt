package photocentre.dataClasses

import photocentre.enums.Urgency
import photocentre.enums.OrderType
import java.sql.Date

data class Order
(
        val id: Long?,
        val urgent: Boolean,
        val discount: Int = 0,
        val cost: Float = 0f,
        val date: Date = Date(java.util.Date().time),
        val completionDate: Date? = null,
        val type: OrderType,
        val branchOffice: BranchOffice,
        val kiosk: Kiosk? = null,
        val customer: Customer
)