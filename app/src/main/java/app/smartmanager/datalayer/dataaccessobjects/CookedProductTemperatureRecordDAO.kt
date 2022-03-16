package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.datalayer.entity.CookedProductTemperatureRecord
import java.util.*

@Dao
interface CookedProductTemperatureRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCookedProductTemperatureRecord(cookedProductTemperatureRecord: CookedProductTemperatureRecord)

    @Query("SELECT * from cooked_product_temperature_record ORDER BY id DESC")
    fun readAllCookedProductTemperatureRecordData(): LiveData<List<CookedProductTemperatureRecord>>

    @Update
    fun updateCookedProductTemperatureRecord(cookedProductTemperatureRecord: CookedProductTemperatureRecord)

    @Delete
    fun deleteCookedProductTemperatureRecord(cookedProductTemperatureRecord: CookedProductTemperatureRecord)

    // Query to retrieve all data from table 'cooked_product_temperature_record'
    @Query("Select * from cooked_product_temperature_record ORDER BY id DESC")
    fun getAllCookedProductTemperatureRecordData(): List<CookedProductTemperatureRecord>?


    //Query to retrieve data for x days
    @Query("SELECT * FROM cooked_product_temperature_record WHERE timestamp >= :oldDate")
    fun generateReport(oldDate: Date): List<CookedProductTemperatureRecord>
}