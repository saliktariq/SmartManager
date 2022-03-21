package app.smartmanager.dataObjectTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.CleaningTaskDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.CleaningTask
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class CleaningTaskTests{
    private lateinit var cleaningTaskDAO: CleaningTaskDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        cleaningTaskDAO = database.cleaningTaskDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun createInsertRetrieveObjectTest() {

        //creating object
        val newObject = CleaningTask(0,"task1",null,null)

        //inserting object in db
        cleaningTaskDAO.addCleaningTask(newObject)

        //retrieving object from db
        val retrievedObject = cleaningTaskDAO.tasksListForTesting()



        //Checking if retrieved data is equal to inserted data
        Assert.assertEquals("task1", retrievedObject.first())

    }

}