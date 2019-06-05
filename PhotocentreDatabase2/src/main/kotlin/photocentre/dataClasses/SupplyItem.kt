package photocentre.dataClasses

import photocentre.enums.ItemType

data class SupplyItem
(
        val id: Long? = null,
        val name: String = "",
        val amount: Int = 0,
        val type: ItemType? = null,
        val supply: Supply? = null
)