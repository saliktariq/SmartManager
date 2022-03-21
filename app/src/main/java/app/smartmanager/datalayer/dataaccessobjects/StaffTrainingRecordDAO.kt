package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.StaffTrainingRecord
import app.smartmanager.datalayer.entity.StaffTrainingTopic
import app.smartmanager.datalayer.entity.Supplier

@Dao
interface StaffTrainingRecordDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addStaffTrainingRecord(staffTrainingRecord: StaffTrainingRecord)

    @Query("SELECT * from staff_training_record ORDER BY id DESC")
    fun readAllStaffTrainingRecordData(): LiveData<List<StaffTrainingRecord>>

    @Update
    fun updateStaffTrainingRecord(staffTrainingRecord: StaffTrainingRecord)

    @Delete
    fun deleteStaffTrainingRecord(staffTrainingRecord: StaffTrainingRecord)

    //Query to retrieve all staff Training record to be used by other classes
    @Query("Select * from staff_training_record ORDER BY id DESC")
    fun readStaffTrainingRecord(): LiveData<List<StaffTrainingRecord>>

    @Query("Select * from staff_training_record ORDER BY id DESC")
    fun readStaffTrainingRecordForTesting(): List<StaffTrainingRecord>


}
