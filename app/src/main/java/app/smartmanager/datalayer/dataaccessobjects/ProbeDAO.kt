package app.smartmanager.datalayer.dataaccessobjects

import androidx.room.Dao
import androidx.room.Delete
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

    // Query to retrieve data related to a probe based on given probeName
    @Query("Select * from probe WHERE probeName = :key ORDER BY probeName DESC LIMIT 1")
    fun getProbeByName(key: String): Probe?

    //Query to update probeName based on probeName
    @Query("UPDATE probe SET probeName = :newProbeName WHERE probeName = :givenProbeName")
    fun updateProbeName(newProbeName: String, givenProbeName: String)

    //Query to delete a record based on a given probeName
    @Query("DELETE from probe WHERE probeName = :givenProbeName")
    fun deleteProbeByName(givenProbeName: String)

    //Query to delete a record based on a given probe object
    @Delete
    fun deleteProbeByProbeObject(probe: Probe)
}