package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "probe_calibration_record")
data class ProbeCalibrationRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var probe: String,
    var temperature: Float,
    var calibrationMethod: String,
    var date: Date

)