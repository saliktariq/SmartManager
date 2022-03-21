package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import java.util.*

@Parcelize
@Entity(tableName = "staff_training_record")
data class StaffTrainingRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val training_topic: String,
    val staff_name: String,
    val timestamp: Date
): Parcelable