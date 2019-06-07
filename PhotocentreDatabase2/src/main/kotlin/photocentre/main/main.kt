package photocentre.main

import photocentre.controllers.*
import photocentre.dao.*
import photocentre.executors.*

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            //DB
            val db = Db()
            val photocentreDataSource = PhotocentreDataSource(db.dataSource)
            
            //DAO
            val amateurDao = AmateurDao(photocentreDataSource)
            val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
            val customerDao = CustomerDao(photocentreDataSource)
            val filmDao = FilmDao(photocentreDataSource)
            val kioskDao = KioskDao(photocentreDataSource)
            val officeItemDao = OfficeItemDao(photocentreDataSource)
            val orderDao = OrderDao(photocentreDataSource)
            val photoDao = PhotoDao(photocentreDataSource)
            val professionalDao = ProfessionalDao(photocentreDataSource)
            val serviceDao = ServiceDao(photocentreDataSource)
            val soldItemDao = SoldItemDao(photocentreDataSource)
            val supplierDao = SupplierDao(photocentreDataSource)
            val supplyDao = SupplyDao(photocentreDataSource)
            val supplyItemDao = SupplyItemDao(photocentreDataSource)
            val workerDao = WorkerDao(photocentreDataSource)
            
            //executors
            val amateurExecutor = AmateurExecutor(photocentreDataSource, amateurDao)
            val branchOfficeExecutor = BranchOfficeExecutor(photocentreDataSource, branchOfficeDao)
            val customerExecutor = CustomerExecutor(photocentreDataSource, customerDao)
            val filmExecutor = FilmExecutor(photocentreDataSource, filmDao)
            val kioskExecutor = KioskExecutor(photocentreDataSource, kioskDao)
            val officeItemExecutor = OfficeItemExecutor(photocentreDataSource, officeItemDao)
            val orderExecutor = OrderExecutor(photocentreDataSource, orderDao)
            val photoExecutor = PhotoExecutor(photocentreDataSource, photoDao)
            val professionalExecutor = ProfessionalExecutor(photocentreDataSource, professionalDao)
            val serviceExecutor = ServiceExecutor(photocentreDataSource, serviceDao)
            val soldItemExecutor = SoldItemExecutor(photocentreDataSource, soldItemDao)
            val supplierExecutor = SupplierExecutor(photocentreDataSource, supplierDao)
            val supplyExecutor = SupplyExecutor(photocentreDataSource, supplyDao)
            val supplyItemExecutor = SupplyItemExecutor(photocentreDataSource, supplyItemDao)
            val workerExecutor = WorkerExecutor(photocentreDataSource, workerDao)
            
            //controllers
            val amateurController = AmateurController(amateurExecutor)
            val branchOfficeController = BranchOfficeController(branchOfficeExecutor)
            val customerController = CustomerController(customerExecutor)
            val filmController = FilmController(filmExecutor)
            val kioskController = KioskController(kioskExecutor)
            val officeItemController = OfficeItemController(officeItemExecutor)
            val orderController = OrderController(orderExecutor)
            val photoController = PhotoController(photoExecutor)
            val professionalController = ProfessionalController(professionalExecutor)
            val serviceController = ServiceController(serviceExecutor)
            val soldItemController = SoldItemController(soldItemExecutor)
            val supplierController = SupplierController(supplierExecutor)
            val supplyController = SupplyController(supplyExecutor)
            val supplyItemController = SupplyItemController(supplyItemExecutor)
            val workerController = WorkerController(workerExecutor)


        }
    }
}