package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import app.smartmanager.datalayer.entity.CleaningTask
import app.smartmanager.datalayer.entity.Equipment


@Dao
interface CleaningTaskDAO {


    @Insert(onConflict = IGNORE)
    fun addCleaningTask(cleaningTask: CleaningTask)

    @Query("SELECT * from cleaningTask ORDER BY id ASC")
    fun readAllCleaningTaskData(): LiveData<List<CleaningTask>>

    @Update
    fun updateCleaningTask(cleaningTask: CleaningTask)

    @Delete
    fun deleteCleaningTask(cleaningTask: CleaningTask)

    @Query("SELECT name from cleaningTask ORDER BY id ASC")
    fun listAllTasks(): LiveData<List<String>>

}