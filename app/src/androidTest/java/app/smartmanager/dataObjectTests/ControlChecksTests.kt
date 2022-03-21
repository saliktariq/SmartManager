package app.smartmanager.dataObjectTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.ControlChecksDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.ControlChecks
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class ControlChecksTests{
    private lateinit var controlChecksDAO: ControlChecksDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        controlChecksDAO = database.controlChecksDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun createInsertRetrieveObjectTest() {

        //creating object
        val newObject = ControlChecks(0,"control_1",null)

        //inserting object in db
        controlChecksDAO.addControlChecks(newObject)

        //retrieving object from db
        val retrievedObject = controlChecksDAO.readDataForUnitTests()



        //Checking if retrieved data is equal to inserted data
        Assert.assertEquals("control_1", retrievedObject.first().name)

    }

}