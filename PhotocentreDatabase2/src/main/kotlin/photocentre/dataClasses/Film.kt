package photocentre.dataClasses

data class Film (
        val id: Long?,
        val name: String,
        val soldItem: SoldItem?,
        val order: Order
)
