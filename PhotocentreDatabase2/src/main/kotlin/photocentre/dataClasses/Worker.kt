package photocentre.dataClasses

import photocentre.enums.AreaOfWork
import photocentre.enums.Position

data class Worker
(
        val id: Long? = null,
        val name: String = "",
        val areaOfWork: AreaOfWork? = null,
        val position: Position? = null
)