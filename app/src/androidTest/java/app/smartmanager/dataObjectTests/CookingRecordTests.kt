package app.smartmanager.dataObjectTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.CookingRecordDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.CookingRecord
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
class CookingRecordTests{
    private lateinit var cookingRecordDAO: CookingRecordDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        cookingRecordDAO = database.cookingRecordDAO
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
        val newObject = CookingRecord(0,"product_1",10, date)

        //inserting object in db
        cookingRecordDAO.addCookingRecord(newObject)

        //retrieving object from db
        val retrievedObject = cookingRecordDAO.readAllCookingRecordData()



        //Checking if retrieved data is equal to inserted data
        Assert.assertEquals("product_1", retrievedObject.first().cooked_product_name)
        Assert.assertEquals(10, retrievedObject.first().quantity)
        Assert.assertEquals(date, retrievedObject.first().timestamp)

    }

}