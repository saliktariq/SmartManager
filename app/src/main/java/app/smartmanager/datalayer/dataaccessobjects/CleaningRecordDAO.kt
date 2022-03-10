package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.CleaningRecord


@Dao
interface CleaningRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCleaningRecord(cleaningRecord: CleaningRecord)

    @Query("SELECT * from cleaning_record ORDER BY id ASC")
    fun readAllCleaningRecordData(): LiveData<List<CleaningRecord>>

    @Update
    fun updateCleaningRecord(cleaningRecord: CleaningRecord)

    @Delete
    fun deleteCleaningRecord(cleaningRecord: CleaningRecord)
}