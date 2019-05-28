package photocentre

import java.sql.Date

data class Service(val id: Long?, val name: String, val cost: Float, val date: Date, val branchOffice: BranchOffice)