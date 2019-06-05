package photocentre.dataClasses

import photocentre.enums.ItemType

data class SupplyItem
(
        val id: Long?,
        val name: String = "",
        val amount: Int = 0,
        val type: ItemType,
        val supply: Supply
)