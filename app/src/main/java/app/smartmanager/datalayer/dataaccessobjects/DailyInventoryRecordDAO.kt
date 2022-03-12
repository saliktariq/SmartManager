package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.DailyInventoryRecord

@Dao
interface DailyInventoryRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDailyInventoryRecord(dailyInventoryRecord: DailyInventoryRecord)

    @Query("SELECT * from daily_inventory_record ORDER BY id DESC")
    fun readAllDailyInventoryRecordData(): List<DailyInventoryRecord>
//    fun readAllDailyInventoryRecordData(): LiveData<List<DailyInventoryRecord>>

    @Update
    fun updateDailyInventoryRecord(dailyInventoryRecord: DailyInventoryRecord)

    @Delete
    fun deleteDailyInventoryRecord(dailyInventoryRecord: DailyInventoryRecord)
}