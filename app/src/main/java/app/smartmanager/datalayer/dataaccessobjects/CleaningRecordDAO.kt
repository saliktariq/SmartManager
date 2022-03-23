package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.datalayer.entity.Probe
import java.util.*


@Dao
interface CleaningRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCleaningRecord(cleaningRecord: CleaningRecord)

    @Query("SELECT * from cleaning_record ORDER BY id DESC")
    fun readAllCleaningRecordData(): LiveData<List<CleaningRecord>>

    @Update
    fun updateCleaningRecord(cleaningRecord: CleaningRecord)

    @Delete
    fun deleteCleaningRecord(cleaningRecord: CleaningRecord)

    // Query to retrieve all data from table 'cleaning_record' List output
    @Query("Select * from cleaning_record ORDER BY id DESC")
    fun getAllCleaningRecordData(): List<CleaningRecord>?

    //Query to retrieve data for x days
    @Query("SELECT * FROM cleaning_record WHERE timestamp >= :arg0")
    fun cleaningReport(arg0: Date): List<CleaningRecord>
}