package app.smartmanager.ui.setup.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.CleaningTask
import app.smartmanager.datalayer.entity.Equipment
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
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

    fun updateCleaningTask(cleaningTask: CleaningTask){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCleaningTask(cleaningTask)
        }
    }

    fun deleteCleaningTask(cleaningTask: CleaningTask){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCleaningTask(cleaningTask)
        }
    }

    fun insertData(name: String, description: String?, frequency: String?): Boolean{
        if(HelperFunctions.checkInputData(name)){
            //creating Cleaning Task object
            val cleaningTask = CleaningTask(0, name, description, frequency)

            //Calling ViewModel function to add cleaning task object to database
            addCleaningTask(cleaningTask)

            ToastMaker.showToast("Cleaning Task added successfully", GetAppContext.appContext)
            return true
        } else {
            ToastMaker.showToast("Enter Cleaning Task name", GetAppContext.appContext)
            return false
        }
    }

    fun updateData(cleaningTaskID: Long, name: String, description: String?, frequency: String?): Boolean{
        if(HelperFunctions.checkInputData(name)){
            //creating Cleaning Task object
            val cleaningTask = CleaningTask(cleaningTaskID, name, description, frequency)

            //Calling ViewModel function to add cleaning task object to database
            updateCleaningTask(cleaningTask)

            ToastMaker.showToast("Cleaning Task updated successfully", GetAppContext.appContext)
            return true
        } else {
            ToastMaker.showToast("Enter Cleaning Task name", GetAppContext.appContext)
            return false
        }
    }

}