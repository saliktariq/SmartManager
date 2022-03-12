package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.datalayer.entity.Probe


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

    // Query to retrieve all data from table 'cleaning_record'
    @Query("Select * from cleaning_record ORDER BY id DESC")
    fun getAllCleaningRecordData(): List<CleaningRecord>?
}