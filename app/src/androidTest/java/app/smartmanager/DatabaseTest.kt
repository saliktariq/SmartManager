package app.smartmanager

import app.smartmanager.datalayer.database.SmartManagerDB
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException



@RunWith(AndroidJUnit4::class)
class DatabaseTest{
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()


    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun databaseExistsTest() {
        assertNotNull(database)
    }
}