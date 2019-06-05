package photocentre.dataClasses

import photocentre.enums.ItemType

data class Supplier
(
        val id: Long? = null,
        val name: String = "",
        val specialization: ItemType? = null
)