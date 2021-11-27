package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.ProbeCalibrationRecord
import app.smartmanager.datalayer.entity.Supplier

@Dao
interface ProbeCalibrationRecordDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProbeCalibrationRecord(record: ProbeCalibrationRecord)

    @Query("SELECT * from probe_calibration_record ORDER BY id ASC")
    fun readAllProbeCalibrationRecordData(): LiveData<List<ProbeCalibrationRecord>>

    @Update
    fun updateProbeCalibrationRecord(record: ProbeCalibrationRecord)

    @Delete
    fun deleteProbeCalibrationRecord(record: ProbeCalibrationRecord)
}