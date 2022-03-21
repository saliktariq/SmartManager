package app.smartmanager.dataObjectTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.EquipmentTemperatureRecordDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.EquipmentTemperatureRecord
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
class EquipmentTemperatureRecordTests{
    private lateinit var equipmentTemperatureRecordDAO: EquipmentTemperatureRecordDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        equipmentTemperatureRecordDAO = database.equipmentTemperatureRecordDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun createInsertRetrieveObjectTest() {

        //creating object
        val newObject = EquipmentTemperatureRecord(0,"equipment_1",10F,Calendar.getInstance().time)

        //inserting object in db
        equipmentTemperatureRecordDAO.addEquipmentTemperatureRecord(newObject)

        //retrieving object from db
        val retrievedObject = equipmentTemperatureRecordDAO.getAllEquipmentTemperatureRecordData()



        //Checking if retrieved data is equal to inserted data
        Assert.assertEquals("equipment_1", retrievedObject?.first()?.equipment_name)
        Assert.assertEquals(10F, retrievedObject?.first()?.temperature)


    }

}