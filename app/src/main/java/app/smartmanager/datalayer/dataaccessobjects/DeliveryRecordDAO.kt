package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.DailyInventoryRecord
import app.smartmanager.datalayer.entity.DeliveryRecord
import java.util.*


@Dao
interface DeliveryRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDeliveryRecord(deliveryRecord: DeliveryRecord)

    @Query("SELECT * from delivery_record ORDER BY id DESC")
    fun readAllDeliveryRecordData(): List<DeliveryRecord>

    @Update
    fun updateDeliveryRecord(deliveryRecord: DeliveryRecord)

    @Delete
    fun deleteDeliveryRecord(deliveryRecord: DeliveryRecord)

    //Query to retrieve data for x days
    @Query("SELECT * FROM delivery_record WHERE timestamp >= :arg0")
    fun generateReport(arg0: Date): List<DeliveryRecord>
}