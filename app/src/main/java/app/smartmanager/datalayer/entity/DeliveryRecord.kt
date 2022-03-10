package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import java.util.*

@Parcelize
@Entity(tableName = "delivery_record")
data class DeliveryRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val product_name: String,
    val supplier: String,
    val quantity: Int,
    val product_temperature: Float?,
    val timestamp: Date
): Parcelable