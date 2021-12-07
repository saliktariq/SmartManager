package app.smartmanager.ui.foodsafetymanagement.proberecord.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.Probe
import app.smartmanager.datalayer.entity.ProbeCalibrationRecord
import app.smartmanager.datalayer.entity.Supplier
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProbeCalibrationRecordViewModel(application: Application) : AndroidViewModel(application) {

    val getAllProbeNames: LiveData<List<String>>

//    var currentTime: Date = Calendar.getInstance().time
//    var formattedDate: String = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime)




    //Retrieve instance of SmartManager's Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init {

        getAllProbeNames = repository.getAllProbeNames

    }








}