package app.smartmanager.ui.foodsafetymanagement.cleaningrecord.viewmodel

import android.app.Application
import androidx.lifecycle.*
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CleaningRecordViewModel(application: Application) : AndroidViewModel(application){

    //Variable to hold fragment data
    var taskName: String = ""

    //Variable to hold Mutable LiveData retrieved from DB
    var allData: MutableLiveData<List<CleaningRecord>>

    //LiveData Observer
    fun getAllDataObserver(): MutableLiveData<List<CleaningRecord>>{
        return allData
    }

    val listAllTasks: LiveData<List<String>>

    //Retrieve instance of repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init{
        listAllTasks = repository.listAllTasks
        allData = MutableLiveData()
        viewModelScope.launch {
            getAllData()
        }
    }

    // Function to retrieve all Data
    suspend fun getAllData(){
        val run = viewModelScope.launch {
            // Variable to hold all data retrieved
            val list = repository.getAllCleaningRecordData()
            allData.postValue(list)
        }
        run.join()

    }

    //Function to delete a single record by giving that object as input to the function
    fun deleteCleaningRecord(cleaningRecord: CleaningRecord){
        viewModelScope.launch(Dispatchers.IO) {
                repository.deleteCleaningRecord(cleaningRecord)
                getAllData()
        }
    }

    fun addCleaningRecord(cleaningRecord: CleaningRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCleaningRecord(cleaningRecord)
        }
    }


}