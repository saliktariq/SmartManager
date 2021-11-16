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
class ProbeInsertandRetrieveTest{
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
        val newProbeData = Probe(1L,"P1")
        GlobalScope.launch {
            probeDAO.insertProbeData(newProbeData)
            val userData = probeDAO.getAllProbeData()
            assertEquals( 1L,userData?.probeID)
            assertEquals( "P1",userData?.probeName)

        }

    }
    @Test
    fun retrieveDataByProbeIDTest() {
        val newProbeData = Probe(2L,"P2")
        GlobalScope.launch {
            probeDAO.insertProbeData(newProbeData)
            val userData = probeDAO.getProbeByID(2L)
            assertEquals( 2L,userData?.probeID)
            assertEquals( "P2",userData?.probeName)

        }

    }
    @Test
    fun retrieveDataByProbeNameTest() {
        val newProbeData = Probe(3L,"P3")
        GlobalScope.launch {
            probeDAO.insertProbeData(newProbeData)
            val userData = probeDAO.getProbeByName("P3")
            Log.d("ProbeID ", userData?.probeID.toString())
            Log.d("ProbeName ", userData?.probeName.toString())
            assertEquals( 3L,userData?.probeID)
            assertEquals( "P3",userData?.probeName)

        }

    }

    @Test
    fun updateProbeNameTest() {
        val newProbeData = Probe(4L,"P4")
        GlobalScope.launch {
            probeDAO.insertProbeData(newProbeData)
            probeDAO.updateProbeName("P5", 4L)
            val userData = probeDAO.getProbeByID(4L)
            Log.d("New ProbeName ", userData?.probeName.toString())
            assertEquals( 4L,userData?.probeID)
            assertEquals( "P5",userData?.probeName)

        }

    }

    @Test
    fun deleteProbeByIDTest() {
        val newProbeData = Probe(6L,"P6")
        GlobalScope.launch {
            probeDAO.insertProbeData(newProbeData)
            probeDAO.deleteProbeByID(6L)
            val userData = probeDAO.getProbeByID(6L)
            Log.d("ProbeID ", userData?.probeID.toString())
            assertEquals( null,userData?.probeID)
            assertEquals( null,userData?.probeName)

        }

    }
}