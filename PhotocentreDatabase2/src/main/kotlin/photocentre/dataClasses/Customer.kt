package photocentre.dataClasses

class Customer
(
        val id: Long? = null,
        val name: String = "",
        val discount: Int = 0,
        val professional: Professional? = null,
        val amateur: Amateur? = null
)