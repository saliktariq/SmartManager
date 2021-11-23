package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import app.smartmanager.datalayer.entity.CleaningTask


@Dao
interface CleaningTaskDAO {


    @Insert(onConflict = IGNORE)
    fun addCleaningTask(cleaningTask: CleaningTask)

    @Query("SELECT * from cleaningTask ORDER BY id ASC")
    fun readAllCleaningTaskData(): LiveData<List<CleaningTask>>

}