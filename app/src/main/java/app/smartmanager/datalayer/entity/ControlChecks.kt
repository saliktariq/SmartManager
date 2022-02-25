package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
@Parcelize
@Entity(tableName = "controlChecks")
data class ControlChecks(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val description: String?

): Parcelable