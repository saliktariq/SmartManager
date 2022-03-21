package app.smartmanager.dataObjectTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.*
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
class CookedProductTemperatureRecordTests{
    private lateinit var cookedProductTemperatureRecordDAO: CookedProductTemperatureRecordDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        cookedProductTemperatureRecordDAO = database.cookedProductTemperatureRecordDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun createInsertRetrieveObjectTest() {

        //creating object
        val date = Calendar.getInstance().time
        val newObject = CookedProductTemperatureRecord(0,"product_1",10f, date)

        //inserting object in db
        cookedProductTemperatureRecordDAO.addCookedProductTemperatureRecord(newObject)

        //retrieving object from db
        val retrievedObject = cookedProductTemperatureRecordDAO.getAllCookedProductTemperatureRecordData()



        //Checking if retrieved data is equal to inserted data
        if (retrievedObject != null) {
            Assert.assertEquals("product_1", retrievedObject.first().cooked_product_name)
        }
        if (retrievedObject != null) {
            Assert.assertEquals(10f, retrievedObject.first().temperature)
        }
        if (retrievedObject != null) {
            Assert.assertEquals(date, retrievedObject.first().timestamp)
        }

    }

}