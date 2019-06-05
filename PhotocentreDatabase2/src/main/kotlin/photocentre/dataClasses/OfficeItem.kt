package photocentre.dataClasses

import photocentre.enums.ItemType

data class OfficeItem
(
        val id: Long? = null,
        val forSale: Boolean,
        val amount: Int = 0,
        val recommendedAmount: Int = 0,
        val criticalAmount: Int = 0,
        val name: String = "",
        val cost: Float = 0f,
        val type: ItemType,
        val branchOffice: BranchOffice
)