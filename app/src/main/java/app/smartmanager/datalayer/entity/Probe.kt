package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Probe(
    @PrimaryKey(autoGenerate = true)
    var probeID: Long,
    var probeName: String?
)