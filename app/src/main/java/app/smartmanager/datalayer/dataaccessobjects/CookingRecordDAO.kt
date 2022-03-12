package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.CookingRecord

@Dao
interface CookingRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCookingRecord(cookingRecord: CookingRecord)

    @Query("SELECT * from cooking_record ORDER BY id ASC")
    fun readAllCookingRecordData(): List<CookingRecord>

    @Update
    fun updateCookingRecord(cookingRecord: CookingRecord)

    @Delete
    fun deleteCookingRecord(cookingRecord: CookingRecord)
}