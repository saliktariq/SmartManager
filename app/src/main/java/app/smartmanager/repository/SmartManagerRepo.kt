package app.smartmanager.repository

import android.content.Context
import app.smartmanager.datalayer.SmartManagerDB
import app.smartmanager.datalayer.entity.*
import app.smartmanager.helper.GetAppContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SmartManagerRepo private constructor(context: Context) {

    /*
    DECLARATION:
    Code (Lines 20-31) for repository to act as *singleton* implemented following instructions from
    The Big Nerd Ranch Guide at
    https://learning.oreilly.com/library/view/android-programming-the/9780135257555/ch11s04.html
     */
    companion object {
        private var INSTANCE: SmartManagerRepo? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = SmartManagerRepo(context)
            }
        }

        fun get(): SmartManagerRepo {
            return INSTANCE ?: throw IllegalStateException("Repository not initialized")
        }
    }


    private val database: SmartManagerDB = SmartManagerDB.getInstance(GetAppContext.appContext!!)

    // DAOs bound to data access objects defined in database class to access functions inside relative DAOs
    private val probeDAO = database.probeDAO



    //Function to insert probe data object in the data source
    suspend fun insertProbeData(probe: Probe) = withContext(Dispatchers.IO) {

        if (probe is Probe) {
            probeDAO.insertProbeData(probe)
        } else {
            throw IllegalArgumentException()
        }
    }

    // Function to retrieve all data from table 'probe' in a List<Probe>
    suspend fun getAllProbeData(): List<Probe>? = withContext(Dispatchers.IO){
        return@withContext probeDAO.getAllProbeData()
    }

    // Function to retrieve data related to a probe based on given probeName
    suspend fun getProbeByName(probeName: String): Probe? = withContext(Dispatchers.IO)  {
        return@withContext probeDAO.getProbeByName(probeName)
    }


    // Function to update probeName based on probeID
    suspend fun updateProbeName(newProbeName: String, givenProbeName: String) = withContext(Dispatchers.IO) {
        return@withContext probeDAO.updateProbeName(newProbeName,givenProbeName)
    }

    // Function to delete a record based on a given probeName
    suspend fun deleteProbeByName(probeName: String) = withContext(Dispatchers.IO) {
        return@withContext probeDAO.deleteProbeByName(probeName)
    }

    // Function to delete a record based on a given probeName
    suspend fun deleteProbeByProbeObject(probe: Probe) = withContext(Dispatchers.IO) {
        return@withContext probeDAO.deleteProbeByProbeObject(probe)
    }

}