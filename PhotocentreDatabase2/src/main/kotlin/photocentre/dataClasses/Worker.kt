package photocentre.dataClasses

import photocentre.enums.AreaOfWork
import photocentre.enums.Position

data class Worker
(
        val id: Long?,
        val name: String,
        val areaOfWork: AreaOfWork,
        val position: Position
)