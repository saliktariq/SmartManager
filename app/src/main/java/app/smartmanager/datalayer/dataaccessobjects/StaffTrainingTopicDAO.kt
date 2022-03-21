package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.StaffTrainingTopic
import app.smartmanager.datalayer.entity.Supplier

@Dao
interface StaffTrainingTopicDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addStaffTrainingTopic(staffTrainingTopic: StaffTrainingTopic)

    @Query("SELECT * from training_topic ORDER BY id DESC")
    fun readAllStaffTrainingTopicData(): LiveData<List<StaffTrainingTopic>>

    @Update
    fun updateStaffTrainingTopic(staffTrainingTopic: StaffTrainingTopic)

    @Delete
    fun deleteStaffTrainingTopic(staffTrainingTopic: StaffTrainingTopic)

    //Query to retrieve all staff Training topic names to be used by other classes
    @Query("Select topic from training_topic ORDER BY id DESC")
    fun readStaffTrainingTopic(): LiveData<List<String>>

    @Query("Select name from supplier ORDER BY id DESC")
    fun readStaffTrainingTopicForUnitTests(): List<StaffTrainingTopic>
}
