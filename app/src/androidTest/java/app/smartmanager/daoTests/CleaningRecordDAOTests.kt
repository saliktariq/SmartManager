package app.smartmanager.daoTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.CleaningRecordDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.CleaningRecord
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
class CleaningRecordDAOTests{
    private lateinit var cleaningRecordDAO: CleaningRecordDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        cleaningRecordDAO = database.cleaningRecordDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun createInsertRetrieveObjectTest() {

        //creating object
        val newObject = CleaningRecord(0,"task2",Calendar.getInstance().time)

        //inserting object in db
        cleaningRecordDAO.addCleaningRecord(newObject)

        //retrieving object from db
        val retrievedObject = cleaningRecordDAO.getAllCleaningRecordData()



        //Checking if retrieved data is equal to inserted data
        Assert.assertEquals("task2", retrievedObject?.first()?.task_name)

    }

}