package app.smartmanager.ui.foodsafetymanagement.stafftrainingrecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.StaffTrainingRecord
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StaffTrainingRecordViewModel(application: Application) : AndroidViewModel(application){
    //Variable to hold Mutable LiveData retrieved from DB
    var allData: MutableLiveData<List<StaffTrainingRecord>>

    //LiveData Observer
    fun getAllDataObserver(): MutableLiveData<List<StaffTrainingRecord>>{
        return allData
    }

    lateinit var listAllTopics: LiveData<List<String>>

    //Retrieve instance of repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init{
        listAllTopics = repository.listAllTopics
        allData = MutableLiveData()
        viewModelScope.launch {
            getAllData()
        }
    }

    // Function to retrieve all Data
    suspend fun getAllData(){
        val run = viewModelScope.launch {
            // Variable to hold all data retrieved
            val list = repository.readStaffTrainingRecordAsList()
            allData.postValue(list)
        }
        run.join()
    }

    //Function to delete a single record by giving that object as input to the function
    fun deleteStaffTrainingRecord(staffTrainingRecord: StaffTrainingRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStaffTrainingRecord(staffTrainingRecord)
            getAllData()
        }
    }

    fun addStaffTrainingRecord(staffTrainingRecord: StaffTrainingRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStaffTrainingRecord(staffTrainingRecord)
        }
    }


}