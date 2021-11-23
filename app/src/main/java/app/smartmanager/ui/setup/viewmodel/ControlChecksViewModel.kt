package app.smartmanager.ui.setup.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.ControlChecks
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ControlChecksViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllControlChecksData: LiveData<List<ControlChecks>>

    //Retrieve instance of SmartManager's Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init {
        //Initialising readAllSupplierData
        readAllControlChecksData = repository.readAllControlChecksData
    }

    fun addControlChecks(controlChecks: ControlChecks){
        viewModelScope.launch(Dispatchers.IO)  {
            repository.addControlChecks(controlChecks)
        }
    }
}