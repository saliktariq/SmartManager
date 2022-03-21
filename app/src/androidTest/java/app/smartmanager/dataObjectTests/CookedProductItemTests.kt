package app.smartmanager.dataObjectTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.CookedProductItemDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.CookedProductItem
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class CookedProductItemTests{
    private lateinit var cookedProductItemDAO: CookedProductItemDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        cookedProductItemDAO = database.cookedProductItemDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun createInsertRetrieveObjectTest() {

        //creating object
        val newObject = CookedProductItem(0,"product_1",100,null)

        //inserting object in db
        cookedProductItemDAO.addCookedProductItemRecord(newObject)

        //retrieving object from db
        val retrievedObject = cookedProductItemDAO.cookedProductItemForUnitTest()



        //Checking if retrieved data is equal to inserted data
        Assert.assertEquals("product_1", retrievedObject.first())

    }

}