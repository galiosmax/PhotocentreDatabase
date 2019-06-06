package photocentre.main

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            /*val db = Db()
            val photocentreDataSource = PhotocentreDataSource(db.dataSource)
            val amateurDao = AmateurDao(photocentreDataSource)
            val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
            val kioskDao = KioskDao(photocentreDataSource)
            val orderDao = OrderDao(photocentreDataSource)
            val photoDao = PhotoDao(photocentreDataSource)
            val serviceDao = ServiceDao(photocentreDataSource)
            val itemDao = ItemDao(photocentreDataSource)
            val supplierDao = SupplierDao(photocentreDataSource)
            val supplyDao = SupplyDao(photocentreDataSource)
            val workerDao = WorkerExecutor(photocentreDataSource)
            val professionalDao = ProfessionalDao(photocentreDataSource)
            val controllers = Controller(Executor(photocentreDataSource, amateurDao, branchOfficeDao, CustomerDao, kioskDao, orderDao,
                    photoDao, serviceDao, itemDao, supplierDao, supplyDao, workerDao, professionalDao, amateurDao))
            generateSequence { print("> "); readLine() }
                    .takeWhile { it != "exit" }
                    .map {
                        try {
                            when {
                                it.trim().startsWith("create") -> when {
                                    //it.trim().contains("photocentre") -> controllers.createPhotocentre(it.substring("create photocentre".length).trim())
                                    it.trim().contains("branch offices") -> controllers.createBranchOffices(it.substring("create branch offices".length).trim())
                                    it.trim().contains("branch office") -> controllers.createBranchOffice(it.substring("create branch office".length).trim())
                                    it.trim().contains("kiosks") -> controllers.createKiosks(it.substring("create kiosks".length).trim())
                                    it.trim().contains("kiosk") -> controllers.createKiosk(it.substring("create kiosk".length).trim())
                                    it.trim().contains("orders") -> controllers.createOrders(it.substring("create orders".length).trim())
                                    it.trim().contains("order") -> controllers.createOrder(it.substring("create order".length).trim())
                                    it.trim().contains("photos") -> controllers.createPhotos(it.substring("create photos".length).trim())
                                    it.trim().contains("photo") -> controllers.createPhoto(it.substring("create photo".length).trim())
                                    it.trim().contains("services") -> controllers.createServices(it.substring("create services".length).trim())
                                    it.trim().contains("service") -> controllers.createService(it.substring("create service".length).trim())
                                    it.trim().contains("items") -> controllers.createItems(it.substring("create items".length).trim())
                                    it.trim().contains("item") -> controllers.createItem(it.substring("create item".length).trim())
                                    it.trim().contains("suppliers") -> controllers.createSuppliers(it.substring("create suppliers".length).trim())
                                    it.trim().contains("supplier") -> controllers.createSupplier(it.substring("create supplier".length).trim())
                                    it.trim().contains("supplies") -> controllers.createSupplies(it.substring("create supplies".length).trim())
                                    it.trim().contains("supply") -> controllers.createSupply(it.substring("create supply".length).trim())
                                    it.trim().contains("workers") -> controllers.createWorkers(it.substring("create workers".length).trim())
                                    it.trim().contains("worker") -> controllers.createWorker(it.substring("create worker".length).trim())
                                    it.trim().contains("professionals") -> controllers.createProfessionals(it.substring("create professionals".length).trim())
                                    it.trim().contains("professional") -> controllers.createProfessional(it.substring("create professional".length).trim())
                                    it.trim().contains("amateurs") -> controllers.createAmateurs(it.substring("create amateurs".length).trim())
                                    it.trim().contains("amateur") -> controllers.createAmateur(it.substring("create amateur".length).trim())
                                    else -> "Unknown command: $it"
                                }
                                it.trim().startsWith("get") -> when {
                                    it.trim().contains("branch office") -> controllers.getBranchOffice(it.substring("get branch office".length).trim())
                                    it.trim().contains("kiosk") -> controllers.getKiosk(it.substring("get kiosk".length).trim())
                                    it.trim().contains("order") -> controllers.getOrder(it.substring("get order".length).trim())
                                    it.trim().contains("photo") -> controllers.getPhoto(it.substring("get photo".length).trim())
                                    it.trim().contains("service") -> controllers.getService(it.substring("get service".length).trim())
                                    it.trim().contains("item") -> controllers.getItem(it.substring("get item".length).trim())
                                    it.trim().contains("supplier") -> controllers.getSupplier(it.substring("get supplier".length).trim())
                                    it.trim().contains("supply") -> controllers.getSupply(it.substring("get supply".length).trim())
                                    it.trim().contains("worker") -> controllers.getWorker(it.substring("get worker".length).trim())
                                    it.trim().contains("professional") -> controllers.getProfessional(it.substring("get professional".length).trim())
                                    it.trim().contains("amateur") -> controllers.getAmateur(it.substring("get amateur".length).trim())
                                    else -> "Unknown command: $it"
                                }
                                it.trim().startsWith("delete") -> when {
                                    it.trim().contains("branch office") -> controllers.deleteBranchOffice(it.substring("delete branch office".length).trim())
                                    it.trim().contains("kiosk") -> controllers.deleteKiosk(it.substring("delete kiosk".length).trim())
                                    it.trim().contains("order") -> controllers.deleteOrder(it.substring("delete order".length).trim())
                                    it.trim().contains("photo") -> controllers.deletePhoto(it.substring("delete photo".length).trim())
                                    it.trim().contains("service") -> controllers.deleteService(it.substring("delete service".length).trim())
                                    it.trim().contains("item") -> controllers.deleteItem(it.substring("delete item".length).trim())
                                    it.trim().contains("supplier") -> controllers.deleteSupplier(it.substring("delete supplier".length).trim())
                                    it.trim().contains("supply") -> controllers.deleteSupply(it.substring("delete supply".length).trim())
                                    it.trim().contains("worker") -> controllers.deleteWorker(it.substring("delete worker".length).trim())
                                    it.trim().contains("professional") -> controllers.deleteProfessional(it.substring("delete professional".length).trim())
                                    it.trim().contains("amateur") -> controllers.deleteAmateur(it.substring("delete amateur".length).trim())
                                    else -> "Unknown command: $it"
                                }
                                else -> "Unknown command: $it"
                            }
                        } catch (e: Exception) {
                            println("Error has been occurred: $e")
                            e.printStackTrace()
                        }
                    }.forEach {
                        println(it)
                    }*/
        }
    }
}