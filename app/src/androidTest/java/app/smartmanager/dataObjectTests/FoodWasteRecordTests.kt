package app.smartmanager.dataObjectTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.FoodWasteRecordDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.FoodWasteRecord
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
class FoodWasteRecordTests{
    private lateinit var foodWasteRecordDAO: FoodWasteRecordDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        foodWasteRecordDAO = database.foodWasteRecordDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun createInsertRetrieveObjectTest() {

        //creating object
        val newObject = FoodWasteRecord(0,"product_1",10,Calendar.getInstance().time)

        //inserting object in db
        foodWasteRecordDAO.addFoodWasteRecord(newObject)

        //retrieving object from db
        val retrievedObject = foodWasteRecordDAO.readAllFoodWasteRecordData()



        //Checking if retrieved data is equal to inserted data
        Assert.assertEquals("product_1", retrievedObject?.first()?.cooked_product_name)
        Assert.assertEquals(10, retrievedObject?.first()?.waste_quantity)


    }

}