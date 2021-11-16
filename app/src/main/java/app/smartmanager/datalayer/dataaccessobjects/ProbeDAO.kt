package app.smartmanager.datalayer.dataaccessobjects

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.smartmanager.datalayer.entity.Probe

@Dao
interface ProbeDAO {

    //Query to insert Probe object
    @Insert
    fun insertProbeData(probe: Probe)

    // Query to retrieve all data from table 'probe'
    @Query("Select * from probe")
    fun getAllProbeData(): List<Probe>?

    // Query to retrieve data related to a probe based on given probeID
    @Query("Select * from probe WHERE probeID = :key ORDER BY probeID DESC LIMIT 1")
    fun getProbeByID(key: Long): Probe?

    // Query to retrieve data related to a probe based on given probeName
    @Query("Select * from probe WHERE probeName = :key ORDER BY probeName DESC LIMIT 1")
    fun getProbeByName(key: String): Probe?

    //Query to update probeName based on probeID
    @Query("UPDATE probe SET probeName = :newProbeName WHERE probeID = :givenProbeID")
    fun updateProbeName(newProbeName: String, givenProbeID: Long)

    //Query to delete a record based on a given probeID
    @Query("DELETE from probe WHERE probeID = :givenProbeID")
    fun deleteProbeByID(givenProbeID: Long)
}