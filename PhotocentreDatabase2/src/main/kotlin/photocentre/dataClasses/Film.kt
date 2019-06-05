package photocentre.dataClasses

data class Film (
        val id: Long? = null,
        val name: String = "",
        val soldItem: SoldItem? = null,
        val order: Order? = null
)
