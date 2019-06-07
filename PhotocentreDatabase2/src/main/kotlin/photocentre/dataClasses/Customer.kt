package photocentre.dataClasses

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import tornadofx.*

class Customer
(
        id: Long? = null,
        name: String = "",
        discount: Int = 0,
        professional: Professional? = null,
        amateur: Amateur? = null
) {
    var id: Long by property(id)
    fun idProperty() = getProperty(Customer::id)

    var name: String by property(name)
    fun nameProperty() = getProperty(Customer::name)

    var discount: Int by property(discount)
    fun discountProperty() = getProperty(Customer::discount)

    var professional: Professional by property(professional)
    fun professionalProperty() = getProperty(Customer::professional)

    var amateur: Amateur by property(amateur)
    fun amateurProperty() = getProperty(Customer::amateur)
}

class CustomerModel : ItemViewModel<Customer>() {
    val customers = SimpleObjectProperty<ObservableList<Customer>>()
    val id = bind(Customer::idProperty)
    val name = bind(Customer::nameProperty)
    val discount = bind(Customer::discountProperty)
    val professional = bind(Customer::professionalProperty)
    val amateur = bind(Customer::amateurProperty)
}