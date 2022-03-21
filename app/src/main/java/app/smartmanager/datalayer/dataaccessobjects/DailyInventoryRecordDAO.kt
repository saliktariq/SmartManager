package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.CookingRecord
import app.smartmanager.datalayer.entity.DailyInventoryRecord
import java.util.*

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


    //Query to retrieve data for x days
    @Query("SELECT * FROM daily_inventory_record WHERE timestamp >= :arg0")
    fun generateReport(arg0: Date): List<DailyInventoryRecord>
}