package app.smartmanager.dataObjectTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.DeliveryRecordDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.DeliveryRecord
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
class DeliveryRecordTests{
    private lateinit var deliveryRecordDAO: DeliveryRecordDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        deliveryRecordDAO = database.deliveryRecordDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun createInsertRetrieveObjectTest() {

        //creating object
        val newObject = DeliveryRecord(0,"product_1","supplier_1",10,10f,Calendar.getInstance().time)

        //inserting object in db
        deliveryRecordDAO.addDeliveryRecord(newObject)

        //retrieving object from db
        val retrievedObject = deliveryRecordDAO.readAllDeliveryRecordData()



        //Checking if retrieved data is equal to inserted data
        Assert.assertEquals("product_1", retrievedObject?.first()?.product_name)
        Assert.assertEquals("supplier_1", retrievedObject?.first()?.supplier)
        Assert.assertEquals(10, retrievedObject?.first()?.quantity)
        Assert.assertEquals(10f, retrievedObject?.first()?.product_temperature)




    }

}