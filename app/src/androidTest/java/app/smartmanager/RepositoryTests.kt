package app.smartmanager


import android.util.Log
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.dataaccessobjects.ProbeDAO
import app.smartmanager.datalayer.entity.Probe

import org.junit.Assert.assertEquals
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.repository.SmartManagerRepo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException



@RunWith(AndroidJUnit4::class)
class RepositoryTests{
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
        val repo = SmartManagerRepo.get()
        GlobalScope.launch {
            repo.insertProbeData(newProbeData)
            val userData = repo.getAllProbeData()

            //The retrieved list only has 1 record, hence using first() function on List<Probe>
            assertEquals( "P1",userData?.first()?.probeName)

        }

    }

    @Test
    fun retrieveDataByProbeNameTest() {
        val newProbeData = Probe("P2")
        val repo = SmartManagerRepo.get()
        GlobalScope.launch {
            repo.insertProbeData(newProbeData)
            val userData = repo.getProbeByName("P2")
            Log.d("ProbeName ", userData?.probeName.toString())
            assertEquals( "P3",userData?.probeName)

        }

    }

    @Test
    fun deleteProbeByNameTest() {
        val newProbeData = Probe("P5")
        val repo = SmartManagerRepo.get()
        GlobalScope.launch {
            repo.insertProbeData(newProbeData)
            repo.deleteProbeByName("P5")
            val userData = repo.getProbeByName("P5")
            Log.d("ProbeID ", userData?.probeName.toString())
            assertEquals( null,userData?.probeName)

        }

    }
}