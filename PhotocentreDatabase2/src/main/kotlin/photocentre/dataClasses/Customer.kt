package photocentre.dataClasses

data class Customer
(
        val id: Long?,
        val name: String = "",
        val discount: Int = 0,
        val professional: Professional? = null,
        val amateur: Amateur? = null
)