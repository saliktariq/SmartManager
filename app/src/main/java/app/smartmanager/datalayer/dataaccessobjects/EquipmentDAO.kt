package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.Equipment
import app.smartmanager.datalayer.entity.Supplier


@Dao
interface EquipmentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addEquipment(equipment: Equipment)

    @Query("SELECT * from equipment ORDER BY id ASC")
    fun readAllEquipmentData(): LiveData<List<Equipment>>

    @Update
    fun updateEquipment(equipment: Equipment)

    @Delete
    fun deleteEquipment(equipment: Equipment)
}