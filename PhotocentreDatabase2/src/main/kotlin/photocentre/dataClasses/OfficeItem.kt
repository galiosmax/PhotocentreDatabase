package photocentre.dataClasses

import photocentre.enums.OfficeItemPurpose
import photocentre.enums.ItemType

data class OfficeItem
(
        val id: Long?,
        val purpose: OfficeItemPurpose,
        val amount: Int,
        val recommendedAmount: Int,
        val criticalAmount: Int,
        val name: String,
        val cost: Float,
        val type: ItemType,
        val branchOffice: BranchOffice
)