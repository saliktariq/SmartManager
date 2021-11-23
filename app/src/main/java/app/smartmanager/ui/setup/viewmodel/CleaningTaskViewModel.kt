package app.smartmanager.ui.setup.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.CleaningTask
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CleaningTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllCleaningTaskData: LiveData<List<CleaningTask>>

    //Retrieve instance of SmartManager's Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init {
        //Initialising readAllSupplierData
        readAllCleaningTaskData = repository.readAllCleaningTaskData
    }

    fun addCleaningTask(cleaningTask: CleaningTask){
        viewModelScope.launch(Dispatchers.IO)  {
            repository.addCleaningTask(cleaningTask)
        }
    }
}