package photocentre.dataClasses

import photocentre.enums.PaperType
import photocentre.enums.PhotoFormat

data class Photo
(
        val id: Long? = null,
        val paperType: PaperType? = null,
        val format: PhotoFormat? = null,
        val film: Film? = null
)