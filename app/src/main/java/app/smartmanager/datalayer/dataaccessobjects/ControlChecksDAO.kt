package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.ControlChecks
import app.smartmanager.datalayer.entity.Supplier


@Dao
interface ControlChecksDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addControlChecks(controlChecks: ControlChecks)

    @Query("SELECT * from controlChecks ORDER BY id DESC")
    fun readAllControlChecksData(): LiveData<List<ControlChecks>>

    @Update
    fun updateControlChecks(controlChecks: ControlChecks)

    @Delete
    fun deleteControlChecks(controlChecks: ControlChecks)

    @Query("SELECT * from controlChecks ORDER BY id DESC")
    fun readDataForUnitTests(): List<ControlChecks>

}