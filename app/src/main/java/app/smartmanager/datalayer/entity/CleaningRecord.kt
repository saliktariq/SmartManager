package app.smartmanager.datalayer.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import java.util.*

@Parcelize
@Entity(tableName = "cleaning_record")
data class CleaningRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val task_name: String,
    val timestamp: Date
): Parcelable