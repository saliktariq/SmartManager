package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.DeliveryRecord


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
}