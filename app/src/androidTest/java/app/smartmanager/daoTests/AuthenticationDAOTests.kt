package app.smartmanager.daoTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.AuthenticationDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.Authentication
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class AuthenticationDAOTests{
    private lateinit var authenticationDAO: AuthenticationDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        authenticationDAO = database.authenticationDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun createInsertRetrieveObjectTest() {

        //creating object
        val newObject = Authentication(0,"salik",2000L,"saliktariq@icloud.com",
        "Salik",1000L)

        //inserting object in db
        authenticationDAO.insertAuthenticationData(newObject)

        //retrieving object from db
            val retrievedObject = authenticationDAO.getUserDataByEmail("saliktariq@icloud.com")

        //Checking if retrieved data is equal to inserted data
        Assert.assertEquals("salik", retrievedObject?.username)

    }

}