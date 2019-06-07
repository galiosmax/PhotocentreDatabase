package photocentre.dataClasses

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import tornadofx.*

class Amateur
(
        id: Long = -1,
        experience: Int = 0
) {
    var id: Long by property(id)
    fun idProperty() = getProperty(Amateur::id)

    var experience: Int by property(experience)
    fun experienceProperty() = getProperty(Amateur::experience)
}

class AmateurModel : ItemViewModel<Amateur>() {
    val amateurs = SimpleObjectProperty<ObservableList<Amateur>>()
    val id = bind(Amateur::idProperty)
    val experience = bind(Amateur::experienceProperty)
}