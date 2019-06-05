package photocentre.dataClasses

import photocentre.enums.Urgency
import photocentre.enums.OrderType
import java.sql.Date

data class Order
(
        val id: Long?,
        val urgency: Urgency,
        val discount: Int?,
        val cost: Float,
        val date: Date,
        val completionDate: Date?,
        val type: OrderType,
        val branchOffice: BranchOffice,
        val kiosk: Kiosk?,
        val customer: Customer
)