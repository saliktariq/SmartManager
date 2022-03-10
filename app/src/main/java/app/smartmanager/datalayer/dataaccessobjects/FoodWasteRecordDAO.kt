package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.FoodWasteRecord

@Dao
interface FoodWasteRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFoodWasteRecord(foodWasteRecord: FoodWasteRecord)

    @Query("SELECT * from food_waste_record ORDER BY id ASC")
    fun readAllFoodWasteRecordData(): LiveData<List<FoodWasteRecord>>

    @Update
    fun updateFoodWasteRecord(foodWasteRecord: FoodWasteRecord)

    @Delete
    fun deleteFoodWasteRecord(foodWasteRecord: FoodWasteRecord)
}