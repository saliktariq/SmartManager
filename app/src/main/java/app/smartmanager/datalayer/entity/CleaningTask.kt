package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
@Parcelize
@Entity(tableName = "cleaningTask")
data class CleaningTask(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val description: String?,
    val frequency: String?
): Parcelable