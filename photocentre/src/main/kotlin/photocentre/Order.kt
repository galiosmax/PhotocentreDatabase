package photocentre

import java.util.*

data class Order(val id: Long?, val urgency: String, val amountOfPhotos: Int, val discount: Int?, val cost: Float, val orderDate: Date, val completionDate: Date?, val type: String)