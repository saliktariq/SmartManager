package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.CookedProductTemperatureRecord
import app.smartmanager.datalayer.entity.CookingRecord
import java.util.*

@Dao
interface CookingRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCookingRecord(cookingRecord: CookingRecord)

    @Query("SELECT * from cooking_record ORDER BY id DESC")
    fun readAllCookingRecordData(): List<CookingRecord>

    @Update
    fun updateCookingRecord(cookingRecord: CookingRecord)

    @Delete
    fun deleteCookingRecord(cookingRecord: CookingRecord)

    //Query to retrieve data for x days
    @Query("SELECT * FROM cooking_record WHERE timestamp >= :oldDate")
    fun generateReport(oldDate: Date): List<CookingRecord>
}