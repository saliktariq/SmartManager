package app.smartmanager.datalayer.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "training_topic")
data class StaffTrainingTopic(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val topic: String,

): Parcelable