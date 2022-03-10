package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import java.util.*

@Parcelize
@Entity(tableName = "equipment_temperature_record")
data class EquipmentTemperatureRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val equipment_name: String,
    val temperature: Float,
    val timestamp: Date
): Parcelable