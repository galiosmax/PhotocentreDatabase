package photocentre

import java.sql.Date


data class Order(val id: Long?, val urgency: String, val amountOfPhotos: Int, val discount: Int?, val cost: Float, val date: Date, val completionDate: Date?, val type: String, val branchOffice: BranchOffice)