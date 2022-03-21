package app.smartmanager.dataObjectTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.InventoryItemDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.InventoryItem
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class InventoryItemTests{
    private lateinit var inventoryItemDAO: InventoryItemDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        inventoryItemDAO = database.inventoryItemDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun createInsertRetrieveObjectTest() {

        //creating object
        val newObject = InventoryItem(0,"item_1",null,100)

        //inserting object in db
        inventoryItemDAO.addInventoryItemRecord(newObject)

        //retrieving object from db
        val retrievedObject = inventoryItemDAO.readInventoryItemNameForUnitTests()



        //Checking if retrieved data is equal to inserted data
        Assert.assertEquals("item_1", retrievedObject.first())

    }

}