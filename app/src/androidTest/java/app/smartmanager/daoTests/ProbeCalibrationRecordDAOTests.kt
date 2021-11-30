package app.smartmanager.daoTests

import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.smartmanager.datalayer.dataaccessobjects.ProbeCalibrationRecordDAO
import app.smartmanager.datalayer.dataaccessobjects.ProbeDAO
import app.smartmanager.datalayer.database.SmartManagerDB
import app.smartmanager.datalayer.entity.Probe
import app.smartmanager.datalayer.entity.ProbeCalibrationRecord
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
class ProbeCalibrationRecordDAOTests {
    private lateinit var probeCalibrationRecordDAO: ProbeCalibrationRecordDAO
    private lateinit var database: SmartManagerDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SmartManagerDB::class.java)
            .allowMainThreadQueries()
            .build()
        probeCalibrationRecordDAO = database.probeCalibrationRecordDAO


    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun addProbeCalibrationRecordTest() {

        val dateTimeObject: Date = Calendar.getInstance().time
        val newProbeCalibrationRecord = ProbeCalibrationRecord(0,"Probe 1", 35.50f,"Hot Water", dateTimeObject)
        GlobalScope.launch {
            probeCalibrationRecordDAO.addProbeCalibrationRecord(newProbeCalibrationRecord)


            val retrievedData = probeCalibrationRecordDAO.retrieveData()

            //The retrieved list only has 1 record, hence using first() function on List<Probe>
            Assert.assertEquals("Probe 1", retrievedData.first().probe)
            Log.d("DATE OBJECT", retrievedData.first().date.toString())
            Log.d("TEMPERATURE", retrievedData.first().temperature.toString())

        }

    }



}

