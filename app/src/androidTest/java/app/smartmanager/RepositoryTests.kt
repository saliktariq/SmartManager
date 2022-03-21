package app.smartmanager


import android.provider.Settings
import android.util.Log
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.dataaccessobjects.ProbeDAO

import org.junit.Assert.assertEquals
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.entity.*
import app.smartmanager.repository.SmartManagerRepo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
class RepositoryTests{
    private lateinit var repository: SmartManagerRepo
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        repository = SmartManagerRepo.get()



    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun insertAndRetrieveDataTests() {

        val date = Calendar.getInstance().time

        //Creating Objects
        val authenticationObject = Authentication(0,"salik",2000L,"saliktariq@icloud.com",
            "Salik",1000L)

        val chemicalListCOSHHObject = ChemicalListCOSHH(0,"bleach","cleaning","1:10","careful")

        val cleaningRecordObject = CleaningRecord(0,"task2", Calendar.getInstance().time)

        val cleaningTaskObject = CleaningTask(0,"task1","description","daily")

        val controlChecksObject = ControlChecks(0,"control_1",null)

        val cookedProductItemObject = CookedProductItem(0,"product_1",100,null)

        val cookedProductTemperatureRecordObject = CookedProductTemperatureRecord(0,"product_1",10f, date)

        val cookingRecordObject = CookingRecord(0,"product_1",10, date)

        val dailyInventoryRecordObject = DailyInventoryRecord(0,"product_1",10,Calendar.getInstance().time)

        val deliveryRecordObject = DeliveryRecord(0,"product_1","supplier_1",10,10f,Calendar.getInstance().time)

        val equipmentTemperatureRecordObject = EquipmentTemperatureRecord(0,"equipment_1",10F,Calendar.getInstance().time)

        val equipmentObject = Equipment(0,"Equipment_1")

        val foodWasteRecordObject = FoodWasteRecord(0,"product_1",10,Calendar.getInstance().time)

        val inventoryItemObject = InventoryItem(0,"item_1","supplier_1",100)

        val probeCalibrationRecordObject = ProbeCalibrationRecord(0,"Probe 1", 35.50f,"Hot Water",date)

        val probeObject = Probe("probe_1")

        val supplierObject = Supplier(0,"supplier_1","supplier@supplier.com","02087675445","This is supplier's address")

        //Objects to hold retrieved data
        var retrievedAuthenticationObject: Authentication? = null
        var retrievedChemicalListCOSHHObject: ChemicalListCOSHH? = null
        var retrievedCleaningRecordObject:  CleaningRecord? = null
        var retrievedCleaningTaskObject: String? = null

        //Inserting all objects then retrieve data to test
        GlobalScope.launch {

            //Using Join() function to control execution to make sure one job is finished before second job starts


           val task1 =
               GlobalScope.launch { repository.insertAuthenticationData(authenticationObject.uid,authenticationObject.username,authenticationObject.pwd,
                   authenticationObject.email,authenticationObject.firstName,authenticationObject.authCode) }.join()


            val task1a =
                GlobalScope.launch {
                   retrievedAuthenticationObject =  repository.getUserDataByEmail("saliktariq@icloud.com")
                }.join()

            //Checking if retrieved data is same as inserted object
            Assert.assertEquals(authenticationObject, retrievedAuthenticationObject)

            val task2 =
                GlobalScope.launch {
                    repository.addChemicalListCOSHH(chemicalListCOSHHObject)
                }.join()

            val task2a =
                GlobalScope.launch {
                    retrievedChemicalListCOSHHObject = repository.readAllChemicalListDataForUnitTesting().first()
                }.join()

            //Checking if retrieved data is same as inserted object
            Assert.assertEquals(chemicalListCOSHHObject,retrievedChemicalListCOSHHObject)

            val task3 =
                GlobalScope.launch {
                    repository.addCleaningRecord(cleaningRecordObject)
                }.join()

            val task3a =
                GlobalScope.launch {
                    retrievedCleaningRecordObject = repository.getAllCleaningRecordData()?.first()
                }.join()

            //Checking if retrieved data is same as inserted object
            Assert.assertEquals(cleaningRecordObject,retrievedCleaningRecordObject)

            val task4 =
                GlobalScope.launch {
                    repository.addCleaningTask(cleaningTaskObject)
                }.join()

            val task4a =
                GlobalScope.launch {
                    retrievedCleaningTaskObject = repository.tasksListForTesting().first()
                }.join()

            //Checking if retrieved data is same as inserted object
            Assert.assertEquals(cleaningTaskObject.name,retrievedCleaningTaskObject)

            //Simplifying the assertion tests by filling variables into Assert function

            Assert.assertEquals(controlChecksObject,repository.readControlChecksDataForUnitTests().first())

            Assert.assertEquals(cookedProductItemObject,repository.readAllCookedProductItemDataForTesting().first())

            Assert.assertEquals(cookedProductTemperatureRecordObject,
                repository.getAllCookedProductTemperatureRecordData()?.first())

            Assert.assertEquals(cookingRecordObject,repository.readAllCookingRecordData().first())

            Assert.assertEquals(dailyInventoryRecordObject,repository.readAllDailyInventoryRecordData().first())

            Assert.assertEquals(deliveryRecordObject,repository.readAllDeliveryRecordData().first())

            Assert.assertEquals(equipmentTemperatureRecordObject,
                repository.getAllEquipmentTemperatureRecordData()?.first())

            Assert.assertEquals(equipmentObject,repository.listAllEquipmentForUnitTests().first())

            Assert.assertEquals(foodWasteRecordObject,repository.readAllFoodWasteRecordData().first())

            Assert.assertEquals(inventoryItemObject,repository.readInventoryItemNameForUnitTests().first())

            Assert.assertEquals(probeCalibrationRecordObject,repository.retrieveProbeCalibrationDataForTesting().first())

            Assert.assertEquals(probeObject, repository.getAllProbeData()?.first())

            Assert.assertEquals(supplierObject, repository.readSupplierNameForUnitTests().first())






        }
    }
}