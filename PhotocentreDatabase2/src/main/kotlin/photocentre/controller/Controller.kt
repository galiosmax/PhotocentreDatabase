package photocentre.controller

class Controller(private val executor: Executor) {

//    fun createPhotocentre(string: String): String {
//        val args = string.split(",").map { it.trim() }
//        if (string.isEmpty() || args.size != 1) {
//            return "1 arg expected"
//        }
//
//        val photocentre = Photocentre(null, args[0])
//        return executor.createPhotocentre(photocentre).toString()
//    }

    /*fun createBranchOffice(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 2) {
            return "2 args expected"
        }

        val branchOffice = BranchOffice(null, args[0], args[1].toInt())
        return executor.createBranchOffice(branchOffice).toString()
    }

    fun createBranchOffices(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 2 != 0) {
            return "even args expected"
        }

        val toCreate = ArrayList<BranchOffice>()

        for (i in 0 until args.size step 2) {
            toCreate.add(BranchOffice(null, args[i], args[i + 1].toInt()))
        }
        return executor.createBranchOffices(toCreate).toString()
    }

    fun getBranchOffice(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findBranchOffice(args[0].toLong()).toString()
    }

    fun deleteBranchOffice(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteBranchOffice(args[0].toLong()).toString()
    }

    fun createKiosk(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 2) {
            return "2 args expected"
        }

        val kiosk = Kiosk(null, args[0], args[1].toInt())
        return executor.createKiosk(kiosk).toString()
    }

    fun createKiosks(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 2 != 0) {
            return "even args expected"
        }

        val toCreate = ArrayList<Kiosk>()

        for (i in 0 until args.size step 2) {
            toCreate.add(Kiosk(null, args[i], args[i + 1].toInt()))
        }
        return executor.createKiosks(toCreate).toString()
    }

    fun getKiosk(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findKiosk(args[0].toLong()).toString()
    }

    fun deleteKiosk(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteKiosk(args[0].toLong()).toString()
    }

    fun createOrder(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 7) {
            return "7 args expected"
        }

        val order = Order(null, args[0], args[1].toInt(), args[2].toInt(), args[3].toFloat(), Date.valueOf(args[4]), Date.valueOf(args[5]), args[6])
        return executor.createOrder(order).toString()
    }

    fun createOrders(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 7 != 0) {
            return "args mod 7 expected"
        }

        val toCreate = ArrayList<Order>()

        for (i in 0 until args.size step 7) {
            toCreate.add(Order(null, args[i], args[i + 1].toInt(), args[i + 2].toInt(), args[i + 3].toFloat(), Date.valueOf(args[i + 4]), Date.valueOf(args[i + 5]), args[i + 6]))
        }
        return executor.createOrders(toCreate).toString()
    }

    fun getOrder(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findOrder(args[0].toLong()).toString()
    }

    fun deleteOrder(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteOrder(args[0].toLong()).toString()
    }

    fun createPhoto(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 3) {
            return "3 args expected"
        }

        val photo = Photo(null, args[0], args[1], args[2])
        return executor.createPhoto(photo).toString()
    }

    fun createPhotos(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 3 != 0) {
            return "args mod 3 expected"
        }

        val toCreate = ArrayList<Photo>()

        for (i in 0 until args.size step 3) {
            toCreate.add(Photo(null, args[i], args[i + 1], args[i + 2]))
        }
        return executor.createPhotos(toCreate).toString()
    }

    fun getPhoto(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findPhoto(args[0].toLong()).toString()
    }

    fun deletePhoto(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deletePhoto(args[0].toLong()).toString()
    }

    fun createService(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 3) {
            return "3 args expected"
        }

        val service = Service(null, args[0], args[1].toFloat(), Date.valueOf(args[2]))
        return executor.createService(service).toString()
    }

    fun createServices(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 3 != 0) {
            return "args mod 3 expected"
        }

        val toCreate = ArrayList<Service>()

        for (i in 0 until args.size step 3) {
            toCreate.add(Service(null, args[i], args[i + 1].toFloat(), Date.valueOf(args[i + 2])))
        }
        return executor.createServices(toCreate).toString()
    }

    fun getService(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findService(args[0].toLong()).toString()
    }

    fun deleteService(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteService(args[0].toLong()).toString()
    }

    fun createItem(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 4) {
            return "4 args expected"
        }

        val item = Item(null, args[0], args[1].toFloat(), Date.valueOf(args[2]), args[3])
        return executor.createItem(item).toString()
    }

    fun createItems(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 4 != 0) {
            return "args mod 4 expected"
        }

        val toCreate = ArrayList<Item>()

        for (i in 0 until args.size step 4) {
            toCreate.add(Item(null, args[i], args[i + 1].toFloat(), Date.valueOf(args[i + 2]), args[i + 3]))
        }
        return executor.createItems(toCreate).toString()
    }

    fun getItem(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findItem(args[0].toLong()).toString()
    }

    fun deleteItem(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteItem(args[0].toLong()).toString()
    }

    fun createSupplier(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 2) {
            return "2 args expected"
        }

        val supplier = Supplier(null, args[0], args[1])
        return executor.createSupplier(supplier).toString()
    }

    fun createSuppliers(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 2 != 0) {
            return "args mod 2 expected"
        }

        val toCreate = ArrayList<Supplier>()

        for (i in 0 until args.size step 2) {
            toCreate.add(Supplier(null, args[i], args[i + 1]))
        }
        return executor.createSuppliers(toCreate).toString()
    }

    fun getSupplier(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findSupplier(args[0].toLong()).toString()
    }

    fun deleteSupplier(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteSupplier(args[0].toLong()).toString()
    }

    fun createSupply(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }

        val supply = Supply(null, args[0].toFloat())
        return executor.createSupply(supply).toString()
    }

    fun createSupplies(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty()) {
            return "args expected"
        }

        val toCreate = ArrayList<Supply>()

        for (i in 0 until args.size) {
            toCreate.add(Supply(null, args[i].toFloat()))
        }
        return executor.createSupplies(toCreate).toString()
    }

    fun getSupply(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findSupply(args[0].toLong()).toString()
    }

    fun deleteSupply(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteSupply(args[0].toLong()).toString()
    }

    fun createWorker(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 3) {
            return "3 args expected"
        }

        val worker = Worker(null, args[0], args[1], args[2])
        return executor.createWorker(worker).toString()
    }

    fun createWorkers(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size % 3 != 0) {
            return "args mod 3 expected"
        }

        val toCreate = ArrayList<Worker>()

        for (i in 0 until args.size step 3) {
            toCreate.add(Worker(null, args[i], args[i + 1], args[i + 2]))
        }
        return executor.createWorkers(toCreate).toString()
    }

    fun getWorker(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findWorker(args[0].toLong()).toString()
    }

    fun deleteWorker(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteWorker(args[0].toLong()).toString()
    }

    fun createProfessional(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }

        val professional = Professional(null, args[0].toInt())
        return executor.createProfessional(professional).toString()
    }

    fun createProfessionals(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty()) {
            return "args expected"
        }

        val toCreate = ArrayList<Professional>()

        for (i in 0 until args.size) {
            toCreate.add(Professional(null, args[i].toInt()))
        }
        return executor.createProfessionals(toCreate).toString()
    }

    fun getProfessional(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findProfessional(args[0].toLong()).toString()
    }

    fun deleteProfessional(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteProfessional(args[0].toLong()).toString()
    }

    fun createAmateur(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }

        val amateur = Amateur(null, args[0].toInt())
        return executor.createAmateur(amateur).toString()
    }

    fun createAmateurs(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (string.isEmpty()) {
            return "args expected"
        }

        val toCreate = ArrayList<Amateur>()

        for (i in 0 until args.size) {
            toCreate.add(Amateur(null, args[i].toInt()))
        }
        return executor.createAmateurs(toCreate).toString()
    }

    fun getAmateur(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.findAmateur(args[0].toLong()).toString()
    }

    fun deleteAmateur(string: String): String {
        val args = string.split(",").map { it.trim() }
        if (args.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return executor.deleteAmateur(args[0].toLong()).toString()
    }*/
}