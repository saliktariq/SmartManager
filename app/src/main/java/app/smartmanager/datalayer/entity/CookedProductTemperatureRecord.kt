package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import java.util.*

@Parcelize
@Entity(tableName = "cooked_product_temperature_record")
data class CookedProductTemperatureRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val cooked_product_name: String,
    val temperature: Float,
    val timestamp: Date
): Parcelable