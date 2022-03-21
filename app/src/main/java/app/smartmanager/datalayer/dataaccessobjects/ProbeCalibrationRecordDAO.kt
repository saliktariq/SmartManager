package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.DailyInventoryRecord
import app.smartmanager.datalayer.entity.ProbeCalibrationRecord
import app.smartmanager.datalayer.entity.Supplier
import java.util.*

@Dao
interface ProbeCalibrationRecordDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProbeCalibrationRecord(record: ProbeCalibrationRecord)

    @Query("SELECT * from probe_calibration_record ORDER BY id DESC")
    fun readAllProbeCalibrationRecordData(): LiveData<List<ProbeCalibrationRecord>>

    @Update
    fun updateProbeCalibrationRecord(record: ProbeCalibrationRecord)

    @Delete
    fun deleteProbeCalibrationRecord(record: ProbeCalibrationRecord)

    @Query("SELECT * from probe_calibration_record ORDER BY id DESC")
    fun retrieveData(): List<ProbeCalibrationRecord>


    //Query to retrieve data for x days
    @Query("SELECT * FROM probe_calibration_record WHERE date >= :arg0")
    fun generateReport(arg0: Date): List<ProbeCalibrationRecord>
}