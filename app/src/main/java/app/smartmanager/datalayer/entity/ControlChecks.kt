package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "controlChecks")
data class ControlChecks(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val description: String,
    val type: String
)