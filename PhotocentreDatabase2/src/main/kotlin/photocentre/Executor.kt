package photocentre

import java.lang.Exception

class Executor(private val dataSource: PhotocentreDataSource,
               private val amateurDao: AmateurDao,
               private val branchOfficeDao: BranchOfficeDao,
               private val customerDao: CustomerDao,
               private val itemDao: ItemDao,
               private val kioskDao: KioskDao,
               private val orderDao: OrderDao,
               private val photoDao: PhotoDao,
               private val professionalDao: ProfessionalDao,
               private val serviceDao: ServiceDao,
               private val supplierDao: SupplierDao,
               private val supplyDao: SupplyDao,
               private val workerDao: WorkerDao
) {

    fun createBranchOffice(toCreate: BranchOffice): Long {
        return transaction(dataSource) {
            branchOfficeDao.createBranchOffice(toCreate)
        }
    }

    fun createBranchOffices(toCreate: Iterable<BranchOffice>): List<Long> {
        return transaction(dataSource) {
            branchOfficeDao.createBranchOffices(toCreate)
        }
    }

    fun findBranchOffice(id: Long): BranchOffice? {
        return transaction(dataSource) {
            branchOfficeDao.findBranchOffice(id)
        }
    }

    fun updateBranchOffice(branchOffice: BranchOffice) {
        return transaction(dataSource) {
            branchOfficeDao.updateBranchOffice(branchOffice)
        }
    }

    fun deleteBranchOffice(id: Long) {
        return transaction(dataSource) {
            branchOfficeDao.deleteBranchOffice(id)
        }
    }

    fun createCustomer(toCreate: Customer): Long {
        return transaction(dataSource) {
            customerDao.createCustomer(toCreate)
        }
    }

    fun createCustomers(toCreate: Iterable<Customer>): List<Long> {
        return transaction(dataSource) {
            customerDao.createCustomers(toCreate)
        }
    }

    fun findCustomer(id: Long): Customer? {
        return transaction(dataSource) {
            customerDao.findCustomer(id)
        }
    }

    fun updateCustomer(customer: Customer) {
        return transaction(dataSource) {
            customerDao.updateCustomer(customer)
        }
    }

    fun deleteCustomer(id: Long) {
        return transaction(dataSource) {
            customerDao.deleteCustomer(id)
        }
    }

    fun createKiosk(toCreate: Kiosk): Long {
        return transaction(dataSource) {
            kioskDao.createKiosk(toCreate)
        }
    }

    fun createKiosks(toCreate: Iterable<Kiosk>): List<Long> {
        return transaction(dataSource) {
            kioskDao.createKiosks(toCreate)
        }
    }

    fun findKiosk(id: Long): Kiosk? {
        return transaction(dataSource) {
            kioskDao.findKiosk(id)
        }
    }

    fun updateKiosk(kiosk: Kiosk) {
        return transaction(dataSource) {
            kioskDao.updateKiosk(kiosk)
        }
    }

    fun deleteKiosk(id: Long) {
        return transaction(dataSource) {
            kioskDao.deleteKiosk(id)
        }
    }

    fun createOrder(toCreate: Order): Long {
        return transaction(dataSource) {
            orderDao.createOrder(toCreate)
        }
    }

    fun createOrders(toCreate: Iterable<Order>): List<Long> {
        return transaction(dataSource) {
            orderDao.createOrders(toCreate)
        }
    }

    fun findOrder(id: Long): Order? {
        return transaction(dataSource) {
            orderDao.findOrder(id)
        }
    }

    fun updateOrder(order: Order) {
        return transaction(dataSource) {
            orderDao.updateOrder(order)
        }
    }

    fun deleteOrder(id: Long) {
        return transaction(dataSource) {
            orderDao.deleteOrder(id)
        }
    }

    fun createPhoto(toCreate: Photo): Long {
        return transaction(dataSource) {
            photoDao.createPhoto(toCreate)
        }
    }

    fun createPhotos(toCreate: Iterable<Photo>): List<Long> {
        return transaction(dataSource) {
            photoDao.createPhotos(toCreate)
        }
    }

    fun findPhoto(id: Long): Photo? {
        return transaction(dataSource) {
            photoDao.findPhoto(id)
        }
    }

    fun updatePhoto(photo: Photo) {
        return transaction(dataSource) {
            photoDao.updatePhoto(photo)
        }
    }

    fun deletePhoto(id: Long) {
        return transaction(dataSource) {
            photoDao.deletePhoto(id)
        }
    }

    fun createService(toCreate: Service): Long {
        return transaction(dataSource) {
            serviceDao.createService(toCreate)
        }
    }

    fun createServices(toCreate: Iterable<Service>): List<Long> {
        return transaction(dataSource) {
            serviceDao.createServices(toCreate)
        }
    }

    fun findService(id: Long): Service? {
        return transaction(dataSource) {
            serviceDao.findService(id)
        }
    }

    fun updateService(service: Service) {
        return transaction(dataSource) {
            serviceDao.updateService(service)
        }
    }

    fun deleteService(id: Long) {
        return transaction(dataSource) {
            serviceDao.deleteService(id)
        }
    }

    fun createItem(toCreate: Item): Long {
        return transaction(dataSource) {
            itemDao.createItem(toCreate)
        }
    }

    fun createItems(toCreate: Iterable<Item>): List<Long> {
        return transaction(dataSource) {
            itemDao.createItems(toCreate)
        }
    }

    fun findItem(id: Long): Item? {
        return transaction(dataSource) {
            itemDao.findItem(id)
        }
    }

    fun updateItem(item: Item) {
        return transaction(dataSource) {
            itemDao.updateItem(item)
        }
    }

    fun deleteItem(id: Long) {
        return transaction(dataSource) {
            itemDao.deleteItem(id)
        }
    }

    fun createSupplier(toCreate: Supplier): Long {
        return transaction(dataSource) {
            supplierDao.createSupplier(toCreate)
        }
    }

    fun createSuppliers(toCreate: Iterable<Supplier>): List<Long> {
        return transaction(dataSource) {
            supplierDao.createSuppliers(toCreate)
        }
    }

    fun findSupplier(id: Long): Supplier? {
        return transaction(dataSource) {
            supplierDao.findSupplier(id)
        }
    }

    fun updateSupplier(supplier: Supplier) {
        return transaction(dataSource) {
            supplierDao.updateSupplier(supplier)
        }
    }

    fun deleteSupplier(id: Long) {
        return transaction(dataSource) {
            supplierDao.deleteSupplier(id)
        }
    }

    fun createSupply(toCreate: Supply): Long {
        return transaction(dataSource) {
            supplyDao.createSupply(toCreate)
        }
    }

    fun createSupplies(toCreate: Iterable<Supply>): List<Long> {
        return transaction(dataSource) {
            supplyDao.createSupplies(toCreate)
        }
    }

    fun findSupply(id: Long): Supply? {
        return transaction(dataSource) {
            supplyDao.findSupply(id)
        }
    }

    fun updateSupply(supply: Supply) {
        return transaction(dataSource) {
            supplyDao.updateSupply(supply)
        }
    }

    fun deleteSupply(id: Long) {
        return transaction(dataSource) {
            supplyDao.deleteSupply(id)
        }
    }

    fun createWorker(toCreate: Worker): Long {
        return transaction(dataSource) {
            workerDao.createWorker(toCreate)
        }
    }

    fun createWorkers(toCreate: Iterable<Worker>): List<Long> {
        return transaction(dataSource) {
            workerDao.createWorkers(toCreate)
        }
    }

    fun findWorker(id: Long): Worker? {
        return transaction(dataSource) {
            workerDao.findWorker(id)
        }
    }

    fun updateWorker(worker: Worker) {
        return transaction(dataSource) {
            workerDao.updateWorker(worker)
        }
    }

    fun deleteWorker(id: Long) {
        return transaction(dataSource) {
            workerDao.deleteWorker(id)
        }
    }

    fun createProfessional(toCreate: Professional): Long {
        return transaction(dataSource) {
            professionalDao.createProfessional(toCreate)
        }
    }

    fun createProfessionals(toCreate: Iterable<Professional>): List<Long> {
        return transaction(dataSource) {
            professionalDao.createProfessionals(toCreate)
        }
    }

    fun findProfessional(id: Long): Professional? {
        return transaction(dataSource) {
            professionalDao.findProfessional(id)
        }
    }

    fun updateProfessional(professional: Professional) {
        return transaction(dataSource) {
            professionalDao.updateProfessional(professional)
        }
    }

    fun deleteProfessional(id: Long) {
        return transaction(dataSource) {
            professionalDao.deleteProfessional(id)
        }
    }

    fun createAmateur(toCreate: Amateur): Long {
        return transaction(dataSource) {
            amateurDao.createAmateur(toCreate)
        }
    }

    fun createAmateurs(toCreate: Iterable<Amateur>): List<Long> {
        return transaction(dataSource) {
            amateurDao.createAmateurs(toCreate)
        }
    }

    fun findAmateur(id: Long): Amateur? {
        return transaction(dataSource) {
            amateurDao.findAmateur(id)
        }
    }

    fun updateAmateur(amateur: Amateur) {
        return transaction(dataSource) {
            amateurDao.updateAmateur(amateur)
        }
    }

    fun deleteAmateur(id: Long) {
        return transaction(dataSource) {
            amateurDao.deleteAmateur(id)
        }
    }
}

fun <T> transaction(dataSource: PhotocentreDataSource, body: () -> T) : T {
    dataSource.realGetConnection().use {
        it.autoCommit = false
        dataSource.connection = it
        try {
            val res = body()
            it.commit()
            return res
        } catch (e: Exception) {
            it.rollback()
            throw e
        }
    }
}