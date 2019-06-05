package photocentre.dataClasses

data class Customer
(
        val id: Long?,
        val name: String,
        val discount: Int,
        val professional: Professional?,
        val amateur: Amateur?
)