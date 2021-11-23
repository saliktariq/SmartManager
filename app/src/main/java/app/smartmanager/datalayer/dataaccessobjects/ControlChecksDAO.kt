package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.smartmanager.datalayer.entity.ControlChecks


@Dao
interface ControlChecksDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addControlChecks(controlChecks: ControlChecks)

    @Query("SELECT * from controlChecks ORDER BY id ASC")
    fun readAllControlChecksData(): LiveData<List<ControlChecks>>
}