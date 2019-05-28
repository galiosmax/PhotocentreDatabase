package photocentre

import java.sql.Date

data class Item(val id: Long?, val name: String, val cost: Float, val date: Date?, val type: String, var branchOffice: BranchOffice, var supply: Supply)