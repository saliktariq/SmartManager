package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.datalayer.entity.EquipmentTemperatureRecord

@Dao
interface EquipmentTemperatureRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addEquipmentTemperatureRecord(equipmentTemperatureRecord: EquipmentTemperatureRecord)

    @Query("SELECT * from equipment_temperature_record ORDER BY id ASC")
    fun readAllEquipmentTemperatureRecordData(): LiveData<List<EquipmentTemperatureRecord>>

    @Update
    fun updateEquipmentTemperatureRecord(equipmentTemperatureRecord: EquipmentTemperatureRecord)

    @Delete
    fun deleteEquipmentTemperatureRecord(equipmentTemperatureRecord: EquipmentTemperatureRecord)


    // Query to retrieve all data from table 'cleaning_record'
    @Query("Select * from equipment_temperature_record ORDER BY id DESC")
    fun getAllEquipmentTemperatureRecordData(): List<EquipmentTemperatureRecord>?
}