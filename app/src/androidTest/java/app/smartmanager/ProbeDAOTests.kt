package app.smartmanager


import android.util.Log
import app.smartmanager.datalayer.SmartManagerDB
import app.smartmanager.datalayer.dataaccessobjects.ProbeDAO
import app.smartmanager.datalayer.entity.Probe

import org.junit.Assert.assertEquals
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException



@RunWith(AndroidJUnit4::class)
class ProbeDAOTests{
    private lateinit var probeDAO: ProbeDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        probeDAO = database.probeDAO


    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun insertAndGetProbeDataTest() {
        val newProbeData = Probe("P1")
        GlobalScope.launch {
            probeDAO.insertProbeData(newProbeData)
            val userData = probeDAO.getAllProbeData()

            //The retrieved list only has 1 record, hence using first() function on List<Probe>
            assertEquals( "P1",userData?.first()?.probeName)

        }

    }
    @Test
    fun retrieveDataByProbeNameTest() {
        val newProbeData = Probe("P2")
        GlobalScope.launch {
            probeDAO.insertProbeData(newProbeData)
            val userData = probeDAO.getProbeByName("P2")
            assertEquals( "P2",userData?.probeName)

        }

    }

    @Test
    fun updateProbeNameTest() {
        val newProbeData = Probe("P4")
        GlobalScope.launch {
            probeDAO.insertProbeData(newProbeData)
            probeDAO.updateProbeName("P5", "P4")
            val userData = probeDAO.getProbeByName("P5")
            Log.d("New ProbeName ", userData?.probeName.toString())
            assertEquals( "P5",userData?.probeName)

        }

    }

    @Test
    fun deleteProbeByIDTest() {
        val newProbeData = Probe("P6")
        GlobalScope.launch {
            probeDAO.insertProbeData(newProbeData)
            probeDAO.deleteProbeByName("P6")
            val userData = probeDAO.getProbeByName("P6")
            assertEquals( null,userData?.probeName)

        }

    }
}