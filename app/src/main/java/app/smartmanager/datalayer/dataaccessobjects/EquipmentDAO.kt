package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.smartmanager.datalayer.entity.Equipment


@Dao
interface EquipmentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEquipment(equipment: Equipment)

    @Query("SELECT * from equipment ORDER BY id ASC")
    fun readAllEquipmentData(): LiveData<List<Equipment>>
}