package app.smartmanager.dataObjectTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.DailyInventoryRecordDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.DailyInventoryRecord
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
class DailyInventoryRecordTests{
    private lateinit var dailyInventoryRecordDAO: DailyInventoryRecordDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        dailyInventoryRecordDAO = database.dailyInventoryRecordDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun createInsertRetrieveObjectTest() {

        //creating object
        val newObject = DailyInventoryRecord(0,"product_1",10,Calendar.getInstance().time)

        //inserting object in db
        dailyInventoryRecordDAO.addDailyInventoryRecord(newObject)

        //retrieving object from db
        val retrievedObject = dailyInventoryRecordDAO.readAllDailyInventoryRecordData()



        //Checking if retrieved data is equal to inserted data
        Assert.assertEquals("product_1", retrievedObject.first().product_name)
        Assert.assertEquals(10, retrievedObject.first().quantity)




    }

}