package app.smartmanager.daoTests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.AuthenticationDAO
import app.smartmanager.datalayer.dataaccessobjects.ChemicalListCOSHHDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.ChemicalListCOSHH
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.Observer


@RunWith(AndroidJUnit4::class)
class ChemicalListCOSHHDAOTests{
    private lateinit var chemicalListCOSHHDAO: ChemicalListCOSHHDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        chemicalListCOSHHDAO = database.chemicalListCOSHHDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun createInsertRetrieveObjectTest() {

        //creating object
        val newObject = ChemicalListCOSHH(0,"bleach",null,null,null)

        //inserting object in db
        chemicalListCOSHHDAO.addChemicalListCOSHH(newObject)

        //retrieving object from db
        val retrievedObject = chemicalListCOSHHDAO.readDataQueryForUnitTest()



        //Checking if retrieved data is equal to inserted data
        Assert.assertEquals("bleach", retrievedObject?.first().name)

    }

}