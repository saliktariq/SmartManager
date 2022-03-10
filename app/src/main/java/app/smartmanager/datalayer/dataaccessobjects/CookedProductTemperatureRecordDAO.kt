package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.CookedProductTemperatureRecord

@Dao
interface CookedProductTemperatureRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCookedProductTemperatureRecord(cookedProductTemperatureRecord: CookedProductTemperatureRecord)

    @Query("SELECT * from cooked_product_temperature_record ORDER BY id ASC")
    fun readAllCookedProductTemperatureRecordData(): LiveData<List<CookedProductTemperatureRecord>>

    @Update
    fun updateCookedProductTemperatureRecord(cookedProductTemperatureRecord: CookedProductTemperatureRecord)

    @Delete
    fun deleteCookedProductTemperatureRecord(cookedProductTemperatureRecord: CookedProductTemperatureRecord)
}