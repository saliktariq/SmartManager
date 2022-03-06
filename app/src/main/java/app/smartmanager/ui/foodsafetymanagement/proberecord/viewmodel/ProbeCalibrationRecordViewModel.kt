package app.smartmanager.ui.foodsafetymanagement.proberecord.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.Probe
import app.smartmanager.datalayer.entity.ProbeCalibrationRecord
import app.smartmanager.datalayer.entity.Supplier
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class ProbeCalibrationRecordViewModel(application: Application) : AndroidViewModel(application) {

    val getAllProbeCalibrationRecords: LiveData<List<ProbeCalibrationRecord>>
    val getAllProbeNames: LiveData<List<String>>

//    var currentTime: Date = Calendar.getInstance().time
//    var formattedDate: String = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime)


    //Retrieve instance of SmartManager's Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init {
        //Initialising getAllProbeNames
        getAllProbeCalibrationRecords = repository.readAllProbeCalibrationRecordData
        getAllProbeNames = repository.getAllProbeNames
    }

    fun addProbeCalibrationRecord(record: ProbeCalibrationRecord) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProbeCalibrationRecord(record)
        }


    }

    fun updateProbeCalibrationRecord(record: ProbeCalibrationRecord) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProbeCalibrationRecord(record)
        }

    }

    fun deleteProbeCalibrationRecord(record: ProbeCalibrationRecord) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProbeCalibrationRecord(record)
        }

    }

    fun insertData(
        probe: String,
        temperature: Float,
        calibrationMethod: String,
        date: Date
    ): Boolean {
        if (HelperFunctions.checkInputData(probe)) {
            //Create ProbeCalibrationRecord object
            val probeCalibrationRecord =
                ProbeCalibrationRecord(0, probe, temperature, calibrationMethod, date)

            //Calling viewmodel function to insert data
            addProbeCalibrationRecord(probeCalibrationRecord)
            ToastMaker.showToast(
                "Probe Calibration Record added successfully",
                GetAppContext.appContext
            )
            return true
        } else {
            ToastMaker.showToast("Enter valid probe name", GetAppContext.appContext)
            return false
        }
    }

    fun updateData(
        id: Long,
        probe: String,
        temperature: Float,
        calibrationMethod: String,
        date: Date
    ): Boolean {
        if (HelperFunctions.checkInputData(probe)) {
            //Create ProbeCalibrationRecord object
            val probeCalibrationRecord =
                ProbeCalibrationRecord(id, probe, temperature, calibrationMethod, date)

            //Calling viewmodel function to insert data
            updateProbeCalibrationRecord(probeCalibrationRecord)
            ToastMaker.showToast(
                "Probe Calibration Record updated successfully",
                GetAppContext.appContext
            )
            return true
        } else {
            ToastMaker.showToast("Enter valid probe name", GetAppContext.appContext)
            return false
        }
    }

}








