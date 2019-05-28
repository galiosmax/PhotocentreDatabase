package photocentre

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val db = Db()
            val photocentreDataSource = PhotocentreDataSource(db.dataSource)
            //val photocentreDao = PhotocentreDao(photocentreDataSource)
            val branchOfficeDao = BranchOfficeDao(photocentreDataSource)
            val kioskDao = KioskDao(photocentreDataSource)
            val orderDao = OrderDao(photocentreDataSource)
            val photoDao = PhotoDao(photocentreDataSource)
            val serviceDao = ServiceDao(photocentreDataSource)
            val itemDao = ItemDao(photocentreDataSource)
            val supplierDao = SupplierDao(photocentreDataSource)
            val supplyDao = SupplyDao(photocentreDataSource)
            val workerDao = WorkerDao(photocentreDataSource)
            val professionalDao = ProfessionalDao(photocentreDataSource)
            val amateurDao = AmateurDao(photocentreDataSource)
            val controller = Controller(Executor(photocentreDataSource, /*photocentreDao, */branchOfficeDao, kioskDao, orderDao,
                    photoDao, serviceDao, itemDao, supplierDao, supplyDao, workerDao, professionalDao, amateurDao))
            generateSequence { print("> "); readLine() }
                    .takeWhile { it != "exit" }
                    .map {
                        try {
                            when {
                                it.trim().startsWith("create") -> when {
                                    //it.trim().contains("photocentre") -> controller.createPhotocentre(it.substring("create photocentre".length).trim())
                                    it.trim().contains("branch offices") -> controller.createBranchOffices(it.substring("create branch offices".length).trim())
                                    it.trim().contains("branch office") -> controller.createBranchOffice(it.substring("create branch office".length).trim())
                                    it.trim().contains("kiosks") -> controller.createKiosks(it.substring("create kiosks".length).trim())
                                    it.trim().contains("kiosk") -> controller.createKiosk(it.substring("create kiosk".length).trim())
                                    it.trim().contains("orders") -> controller.createOrders(it.substring("create orders".length).trim())
                                    it.trim().contains("order") -> controller.createOrder(it.substring("create order".length).trim())
                                    it.trim().contains("photos") -> controller.createPhotos(it.substring("create photos".length).trim())
                                    it.trim().contains("photo") -> controller.createPhoto(it.substring("create photo".length).trim())
                                    it.trim().contains("services") -> controller.createServices(it.substring("create services".length).trim())
                                    it.trim().contains("service") -> controller.createService(it.substring("create service".length).trim())
                                    it.trim().contains("items") -> controller.createItems(it.substring("create items".length).trim())
                                    it.trim().contains("item") -> controller.createItem(it.substring("create item".length).trim())
                                    it.trim().contains("suppliers") -> controller.createSuppliers(it.substring("create suppliers".length).trim())
                                    it.trim().contains("supplier") -> controller.createSupplier(it.substring("create supplier".length).trim())
                                    it.trim().contains("supplies") -> controller.createSupplies(it.substring("create supplies".length).trim())
                                    it.trim().contains("supply") -> controller.createSupply(it.substring("create supply".length).trim())
                                    it.trim().contains("workers") -> controller.createWorkers(it.substring("create workers".length).trim())
                                    it.trim().contains("worker") -> controller.createWorker(it.substring("create worker".length).trim())
                                    it.trim().contains("professionals") -> controller.createProfessionals(it.substring("create professionals".length).trim())
                                    it.trim().contains("professional") -> controller.createProfessional(it.substring("create professional".length).trim())
                                    it.trim().contains("amateurs") -> controller.createAmateurs(it.substring("create amateurs".length).trim())
                                    it.trim().contains("amateur") -> controller.createAmateur(it.substring("create amateur".length).trim())
                                    else -> "Unknown command: $it"
                                }
                                it.trim().startsWith("get") -> when {
                                    it.trim().contains("branch office") -> controller.getBranchOffice(it.substring("get branch office".length).trim())
                                    it.trim().contains("kiosk") -> controller.getKiosk(it.substring("get kiosk".length).trim())
                                    it.trim().contains("order") -> controller.getOrder(it.substring("get order".length).trim())
                                    it.trim().contains("photo") -> controller.getPhoto(it.substring("get photo".length).trim())
                                    it.trim().contains("service") -> controller.getService(it.substring("get service".length).trim())
                                    it.trim().contains("item") -> controller.getItem(it.substring("get item".length).trim())
                                    it.trim().contains("supplier") -> controller.getSupplier(it.substring("get supplier".length).trim())
                                    it.trim().contains("supply") -> controller.getSupply(it.substring("get supply".length).trim())
                                    it.trim().contains("worker") -> controller.getWorker(it.substring("get worker".length).trim())
                                    it.trim().contains("professional") -> controller.getProfessional(it.substring("get professional".length).trim())
                                    it.trim().contains("amateur") -> controller.getAmateur(it.substring("get amateur".length).trim())
                                    else -> "Unknown command: $it"
                                }
                                it.trim().startsWith("delete") -> when {
                                    it.trim().contains("branch office") -> controller.deleteBranchOffice(it.substring("delete branch office".length).trim())
                                    it.trim().contains("kiosk") -> controller.deleteKiosk(it.substring("delete kiosk".length).trim())
                                    it.trim().contains("order") -> controller.deleteOrder(it.substring("delete order".length).trim())
                                    it.trim().contains("photo") -> controller.deletePhoto(it.substring("delete photo".length).trim())
                                    it.trim().contains("service") -> controller.deleteService(it.substring("delete service".length).trim())
                                    it.trim().contains("item") -> controller.deleteItem(it.substring("delete item".length).trim())
                                    it.trim().contains("supplier") -> controller.deleteSupplier(it.substring("delete supplier".length).trim())
                                    it.trim().contains("supply") -> controller.deleteSupply(it.substring("delete supply".length).trim())
                                    it.trim().contains("worker") -> controller.deleteWorker(it.substring("delete worker".length).trim())
                                    it.trim().contains("professional") -> controller.deleteProfessional(it.substring("delete professional".length).trim())
                                    it.trim().contains("amateur") -> controller.deleteAmateur(it.substring("delete amateur".length).trim())
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
                    }
        }
    }
}