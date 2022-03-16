package app.smartmanager.repository

import android.content.Context
import androidx.lifecycle.LiveData
import app.smartmanager.datalayer.dataaccessobjects.*
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.*
import app.smartmanager.helper.GetAppContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class SmartManagerRepo private constructor(context: Context) {


    /*
    DECLARATION:
    Code (Lines 20-31) for repository to act as *singleton* implemented following instructions from
    The Big Nerd Ranch Guide at
    https://learning.oreilly.com/library/view/android-programming-the/9780135257555/ch11s04.html
     */
    companion object {
        private var INSTANCE: SmartManagerRepo? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = SmartManagerRepo(context)
            }
        }

        fun get(): SmartManagerRepo {
            return INSTANCE ?: throw IllegalStateException("Repository not initialized")
        }
    }


    private val database: SmartManagerDB = SmartManagerDB.getInstance(GetAppContext.appContext!!)

    // DAOs bound to data access objects defined in database class to access functions inside relative DAOs
    private val probeDAO = database.probeDAO
    private val supplierDAO = database.supplierDAO
    private val equipmentDAO = database.equipmentDAO
    private val controlChecksDAO = database.controlChecksDAO
    private val cleaningTaskDAO = database.cleaningTaskDAO
    private val chemicalListCOSHHDAO = database.chemicalListCOSHHDAO
    private val probeCalibrationRecordDAO = database.probeCalibrationRecordDAO
    private val cookedProductItemDAO = database.cookedProductItemDAO
    private val inventoryItemDAO = database.inventoryItemDAO
    private val cleaningRecordDAO = database.cleaningRecordDAO
    private val equipmentTemperatureRecordDAO = database.equipmentTemperatureRecordDAO
    private val cookedProductTemperatureRecordDAO = database.cookedProductTemperatureRecordDAO
    private val cookingRecordDAO = database.cookingRecordDAO
    private val dailyInventoryRecordDAO = database.dailyInventoryRecordDAO
    private val deliveryRecordDAO = database.deliveryRecordDAO
    private val foodWasteRecordDAO = database.foodWasteRecordDAO


    /*
    ******************- Repository functions related to Probe Entity -******************
     */


    //Function to insert probe data object in the data source
    suspend fun insertProbeData(probe: Probe) = withContext(Dispatchers.IO) {

        if (probe is Probe) {
            probeDAO.insertProbeData(probe)
        } else {
            throw IllegalArgumentException()
        }
    }

    // Function to retrieve all data from table 'probe' in a List<Probe>
    suspend fun getAllProbeData(): List<Probe>? = withContext(Dispatchers.IO) {
        return@withContext probeDAO.getAllProbeData()
    }

//     Function to retrieve data related to a probe based on given probeName
    suspend fun getProbeByName(probeName: String): Probe? = withContext(Dispatchers.IO) {
        return@withContext probeDAO.getProbeByName(probeName)
    }


    // Function to update probeName based on probeID
    suspend fun updateProbeName(newProbeName: String, givenProbeName: String) =
        withContext(Dispatchers.IO) {
            return@withContext probeDAO.updateProbeName(newProbeName, givenProbeName)
        }

    // Function to delete a record based on a given probeName
    suspend fun deleteProbeByName(probeName: String) = withContext(Dispatchers.IO) {
        return@withContext probeDAO.deleteProbeByName(probeName)
    }

    // Function to delete a record based on a given probeName
    suspend fun deleteProbeByProbeObject(probe: Probe) = withContext(Dispatchers.IO) {
        return@withContext probeDAO.deleteProbeByProbeObject(probe)
    }

    //Variable to retrieve and hold  names of probes to be used in ProbeCalibrationRecord

    val getAllProbeNames: LiveData<List<String>> = probeDAO.getAllProbeNames()


    /*
    ******************- Repository functions related to Supplier Entity -******************
     */

    suspend fun addSupplier(supplier: Supplier) {
        supplierDAO.addSupplier(supplier)
    }

    suspend fun updateSupplier(supplier: Supplier) {
        supplierDAO.updateSupplier(supplier)
    }

    suspend fun deleteSupplier(supplier: Supplier) {
        supplierDAO.deleteSupplier(supplier)
    }

    val readAllSupplierData: LiveData<List<Supplier>> = supplierDAO.readAllSupplierData()

    //Variable to retrieve only names of suppliers
    val readSupplierName: LiveData<List<String>> = supplierDAO.readSupplierName()

    /*
    ******************- Repository functions related to Equipment Entity -******************
     */

    suspend fun addEquipment(equipment: Equipment) {
        equipmentDAO.addEquipment(equipment)
    }

    suspend fun updateEquipment(equipment: Equipment) {
        equipmentDAO.updateEquipment(equipment)
    }

    suspend fun deleteEquipment(equipment: Equipment) {
        equipmentDAO.deleteEquipment(equipment)
    }

    val readAllEquipmentData: LiveData<List<Equipment>> = equipmentDAO.readAllEquipmentData()

    val listAllEquipment: LiveData<List<String>> =
        equipmentDAO.listAllEquipment()

    /*
    ******************- Repository functions related to ControlChecks Entity -******************
     */
    suspend fun addControlChecks(controlChecks: ControlChecks) {
        controlChecksDAO.addControlChecks(controlChecks)
    }

    suspend fun updateControlChecks(controlChecks: ControlChecks) {
        controlChecksDAO.updateControlChecks(controlChecks)
    }

    suspend fun deleteControlChecks(controlChecks: ControlChecks) {
        controlChecksDAO.deleteControlChecks(controlChecks)
    }

    val readAllControlChecksData: LiveData<List<ControlChecks>> =
        controlChecksDAO.readAllControlChecksData()

    /*
******************- Repository functions related to CleaningTask Entity -******************
 */

    suspend fun addCleaningTask(cleaningTask: CleaningTask) {
        cleaningTaskDAO.addCleaningTask(cleaningTask)
    }

    suspend fun updateCleaningTask(cleaningTask: CleaningTask) {
        cleaningTaskDAO.updateCleaningTask(cleaningTask)
    }

    suspend fun deleteCleaningTask(cleaningTask: CleaningTask) {
        cleaningTaskDAO.deleteCleaningTask(cleaningTask)
    }

    val readAllCleaningTaskData: LiveData<List<CleaningTask>> =
        cleaningTaskDAO.readAllCleaningTaskData()

    val listAllTasks: LiveData<List<String>> =
        cleaningTaskDAO.listAllTasks()

    /*
******************- Repository functions related to ChemicalListCOSHH Entity -******************
 */

    suspend fun addChemicalListCOSHH(chemicalListCOSHH: ChemicalListCOSHH) {
        chemicalListCOSHHDAO.addChemicalListCOSHH(chemicalListCOSHH)
    }

    suspend fun updateChemicalListCOSHH(chemicalListCOSHH: ChemicalListCOSHH) {
        chemicalListCOSHHDAO.updateChemicalListCOSHH(chemicalListCOSHH)
    }

    suspend fun deleteChemicalListCOSHH(chemicalListCOSHH: ChemicalListCOSHH) {
        chemicalListCOSHHDAO.deleteChemicalListCOSHH(chemicalListCOSHH)
    }

    val readAllChemicalListCOSHHData: LiveData<List<ChemicalListCOSHH>> =
        chemicalListCOSHHDAO.readAllChemicalListCOSHHData()

    /*
******************- Repository functions related to Probe Calibration Record -******************
 */

    suspend fun addProbeCalibrationRecord(record: ProbeCalibrationRecord) {
        probeCalibrationRecordDAO.addProbeCalibrationRecord(record)

    }

    suspend fun updateProbeCalibrationRecord(record: ProbeCalibrationRecord) {
        probeCalibrationRecordDAO.updateProbeCalibrationRecord(record)
    }

    suspend fun deleteProbeCalibrationRecord(record: ProbeCalibrationRecord) {
        probeCalibrationRecordDAO.deleteProbeCalibrationRecord(record)
    }

    val readAllProbeCalibrationRecordData: LiveData<List<ProbeCalibrationRecord>> =
        probeCalibrationRecordDAO.readAllProbeCalibrationRecordData()

    //Function to retrieve data for x old days - used for Reports
    suspend fun generateProbeCalibrationReport(oldDate: Date): List<ProbeCalibrationRecord>{
        return probeCalibrationRecordDAO.generateReport(oldDate)
    }

    /*
******************- Repository functions related to CookedProductItem -******************
*/

    suspend fun addCookedProductItemRecord(record: CookedProductItem) {
        cookedProductItemDAO.addCookedProductItemRecord(record)

    }

    suspend fun updateCookedProductItemRecord(record: CookedProductItem) {
        cookedProductItemDAO.updateCookedProductItemRecord(record)
    }

    suspend fun deleteCookedProductItemRecord(record: CookedProductItem) {
        cookedProductItemDAO.deleteCookedProductItemRecord(record)
    }

    val readAllCookedProductItemRecordData: LiveData<List<CookedProductItem>> =
        cookedProductItemDAO.readAllCookedProductItemData()

    val listAllCookedProductItem: LiveData<List<String>> =
        cookedProductItemDAO.listAllCookedProductItem()


    /*
******************- Repository functions related to InventoryItem -******************
*/

    suspend fun addInventoryItemRecord(record: InventoryItem) {
        inventoryItemDAO.addInventoryItemRecord(record)

    }

    suspend fun updateInventoryItemRecord(record: InventoryItem) {
        inventoryItemDAO.updateInventoryItemRecord(record)
    }

    suspend fun deleteInventoryItemRecord(record: InventoryItem) {
        inventoryItemDAO.deleteInventoryItemRecord(record)
    }

    val readAllInventoryItemRecordData: LiveData<List<InventoryItem>> =
        inventoryItemDAO.readAllInventoryItemData()


    //Variable to retrieve only the list of names of inventory items to be used by other classes
    val readInventoryItemName: LiveData<List<String>> =
        inventoryItemDAO.readInventoryItemName()

    /*
******************- Repository functions related to CleaningRecord -******************
*/

    suspend fun addCleaningRecord(cleaningRecord: CleaningRecord){
        cleaningRecordDAO.addCleaningRecord(cleaningRecord)
    }

    suspend fun updateCleaningRecord(cleaningRecord: CleaningRecord){
        cleaningRecordDAO.updateCleaningRecord(cleaningRecord)
    }

    suspend fun deleteCleaningRecord(cleaningRecord: CleaningRecord){
        cleaningRecordDAO.deleteCleaningRecord(cleaningRecord)
    }

    val readAllCleaningRecordData: LiveData<List<CleaningRecord>> =
        cleaningRecordDAO.readAllCleaningRecordData()


    // Function to retrieve all data from table 'probe' in a List<Probe>
    suspend fun getAllCleaningRecordData(): List<CleaningRecord>? = withContext(Dispatchers.IO) {
        return@withContext cleaningRecordDAO.getAllCleaningRecordData()
    }

    //Function to retrieve data for x old days - used for Reports
    suspend fun  cleaningReport(oldDate: Date): List<CleaningRecord>{
        return cleaningRecordDAO.cleaningReport(oldDate)
    }
    /*
******************- Repository functions related to EquipmentTemperatureRecord -******************
*/
    suspend fun addEquipmentTemperatureRecord(equipmentTemperatureRecord: EquipmentTemperatureRecord){
        equipmentTemperatureRecordDAO.addEquipmentTemperatureRecord(equipmentTemperatureRecord)
    }

    suspend fun updateEquipmentTemperatureRecord(equipmentTemperatureRecord: EquipmentTemperatureRecord){
        equipmentTemperatureRecordDAO.updateEquipmentTemperatureRecord(equipmentTemperatureRecord)
    }

    suspend fun deleteEquipmentTemperatureRecord(equipmentTemperatureRecord: EquipmentTemperatureRecord){
        equipmentTemperatureRecordDAO.deleteEquipmentTemperatureRecord(equipmentTemperatureRecord)
    }

    val readAllEquipmentTemperatureRecordData: LiveData<List<EquipmentTemperatureRecord>> =
        equipmentTemperatureRecordDAO.readAllEquipmentTemperatureRecordData()

    //Function to retrieve data for x old days - used for Reports
    suspend fun generateEquipmentTemperatureReport(oldDate: Date): List<EquipmentTemperatureRecord>{
        return equipmentTemperatureRecordDAO.generateReport(oldDate)
    }


    suspend fun getAllEquipmentTemperatureRecordData(): List<EquipmentTemperatureRecord>? = withContext(Dispatchers.IO) {
        return@withContext equipmentTemperatureRecordDAO.getAllEquipmentTemperatureRecordData()
    }

    /*
******************- Repository functions related to CookedProductTemperatureRecord -******************
*/
    suspend fun addCookedProductTemperatureRecord(cookedProductTemperatureRecord: CookedProductTemperatureRecord){
        cookedProductTemperatureRecordDAO.addCookedProductTemperatureRecord(cookedProductTemperatureRecord)
    }

    suspend fun updateCookedProductTemperatureRecord(cookedProductTemperatureRecord: CookedProductTemperatureRecord){
        cookedProductTemperatureRecordDAO.updateCookedProductTemperatureRecord(cookedProductTemperatureRecord)
    }

    suspend fun deleteCookedProductTemperatureRecord(cookedProductTemperatureRecord: CookedProductTemperatureRecord){
        cookedProductTemperatureRecordDAO.deleteCookedProductTemperatureRecord(cookedProductTemperatureRecord)
    }

    val readAllCookedProductTemperatureRecordData: LiveData<List<CookedProductTemperatureRecord>> =
        cookedProductTemperatureRecordDAO.readAllCookedProductTemperatureRecordData()

    suspend fun getAllCookedProductTemperatureRecordData(): List<CookedProductTemperatureRecord>? = withContext(Dispatchers.IO){
        return@withContext cookedProductTemperatureRecordDAO.getAllCookedProductTemperatureRecordData()
    }

    //Function to retrieve data for x old days - used for Reports
    suspend fun generateCookedProductTemperatureReport(oldDate: Date): List<CookedProductTemperatureRecord>{
        return cookedProductTemperatureRecordDAO.generateReport(oldDate)
    }

    /*
******************- Repository functions related to CookingRecord -******************
*/

    suspend fun addCookingRecord(cookingRecord: CookingRecord){
        cookingRecordDAO.addCookingRecord(cookingRecord)
    }

    suspend fun updateCookingRecord(cookingRecord: CookingRecord){
        cookingRecordDAO.updateCookingRecord(cookingRecord)
    }

    suspend fun deleteCookingRecord(cookingRecord: CookingRecord){
        cookingRecordDAO.deleteCookingRecord(cookingRecord)
    }

    suspend fun readAllCookingRecordData(): List<CookingRecord> = withContext(Dispatchers.IO)
    {
        return@withContext cookingRecordDAO.readAllCookingRecordData()
      }

    //Function to retrieve data for x old days - used for Reports
    suspend fun generateCookingRecordReport(oldDate: Date): List<CookingRecord>{
        return cookingRecordDAO.generateReport(oldDate)
    }

    /*
******************- Repository functions related to DailyInventoryRecord -******************
*/

    suspend fun addDailyInventoryRecord(dailyInventoryRecord: DailyInventoryRecord){
        dailyInventoryRecordDAO.addDailyInventoryRecord(dailyInventoryRecord)
    }

    suspend fun updateDailyInventoryRecord(dailyInventoryRecord: DailyInventoryRecord){
        dailyInventoryRecordDAO.updateDailyInventoryRecord(dailyInventoryRecord)
    }

    suspend fun deleteDailyInventoryRecord(dailyInventoryRecord: DailyInventoryRecord){
        dailyInventoryRecordDAO.deleteDailyInventoryRecord(dailyInventoryRecord)
    }

    suspend fun readAllDailyInventoryRecordData(): List<DailyInventoryRecord> = withContext(Dispatchers.IO){
        return@withContext dailyInventoryRecordDAO.readAllDailyInventoryRecordData()
    }

    //Function to retrieve data for x old days - used for Reports
    suspend fun generateInventoryReport(oldDate: Date): List<DailyInventoryRecord>{
        return dailyInventoryRecordDAO.generateReport(oldDate)
    }

    /*
******************- Repository functions related to DeliveryRecord -******************
*/
    suspend fun addDeliveryRecord(deliveryRecord: DeliveryRecord){
        deliveryRecordDAO.addDeliveryRecord(deliveryRecord)
    }

    suspend fun updateDeliveryRecord(deliveryRecord: DeliveryRecord){
        deliveryRecordDAO.updateDeliveryRecord(deliveryRecord)
    }

    suspend fun deleteDeliveryRecord(deliveryRecord: DeliveryRecord){
        deliveryRecordDAO.deleteDeliveryRecord(deliveryRecord)
    }

    suspend fun readAllDeliveryRecordData(): List<DeliveryRecord> = withContext(Dispatchers.IO){
        return@withContext deliveryRecordDAO.readAllDeliveryRecordData()
    }

    //Function to retrieve data for x old days - used for Reports
    suspend fun generateDeliveryRecordReport(oldDate: Date): List<DeliveryRecord>{
        return deliveryRecordDAO.generateReport(oldDate)
    }
    /*
******************- Repository functions related to FoodWasteRecord -******************
*/
    suspend fun addFoodWasteRecord(foodWasteRecord: FoodWasteRecord){
        foodWasteRecordDAO.addFoodWasteRecord(foodWasteRecord)
    }

    suspend fun updateFoodWasteRecord(foodWasteRecord: FoodWasteRecord){
        foodWasteRecordDAO.updateFoodWasteRecord(foodWasteRecord)
    }

    suspend fun deleteFoodWasteRecord(foodWasteRecord: FoodWasteRecord){
        foodWasteRecordDAO.deleteFoodWasteRecord(foodWasteRecord)
    }
//    val readAllFoodWasteRecordData: LiveData<List<FoodWasteRecord>> =
    suspend fun readAllFoodWasteRecordData(): List<FoodWasteRecord> = withContext(Dispatchers.IO){
        return@withContext foodWasteRecordDAO.readAllFoodWasteRecordData()
}

    //Function to retrieve data for x old days - used for Reports
    suspend fun generateFoodWasteReport(oldDate: Date): List<FoodWasteRecord>{
        return foodWasteRecordDAO.generateReport(oldDate)
    }


}
