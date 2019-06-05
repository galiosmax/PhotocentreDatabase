package photocentre.dataClasses

import photocentre.enums.OfficeItemPurpose
import photocentre.enums.ItemType

data class OfficeItem
(
        val id: Long?,
        val purpose: Boolean,
        val amount: Int = 0,
        val recommendedAmount: Int = 0,
        val criticalAmount: Int = 0,
        val name: String = "",
        val cost: Float = 0f,
        val type: ItemType,
        val branchOffice: BranchOffice
)