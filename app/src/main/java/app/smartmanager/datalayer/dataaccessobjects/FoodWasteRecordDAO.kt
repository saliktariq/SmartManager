package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.DailyInventoryRecord
import app.smartmanager.datalayer.entity.FoodWasteRecord
import java.util.*

@Dao
interface FoodWasteRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFoodWasteRecord(foodWasteRecord: FoodWasteRecord)

    @Query("SELECT * from food_waste_record ORDER BY id DESC")
//    fun readAllFoodWasteRecordData(): LiveData<List<FoodWasteRecord>>
    fun readAllFoodWasteRecordData(): List<FoodWasteRecord>

    @Update
    fun updateFoodWasteRecord(foodWasteRecord: FoodWasteRecord)

    @Delete
    fun deleteFoodWasteRecord(foodWasteRecord: FoodWasteRecord)

    //Query to retrieve data for x days
    @Query("SELECT * FROM food_waste_record WHERE timestamp >= :arg0")
    fun generateReport(arg0: Date): List<FoodWasteRecord>
}