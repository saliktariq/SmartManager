package app.smartmanager.ui.inventorymanagement.dailyinventory.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.datalayer.entity.DailyInventoryRecord
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DailyInventoryViewModel(application: Application) : AndroidViewModel(application){
    //Variable to hold Mutable LiveData retrieved from DB
    var allData: MutableLiveData<List<DailyInventoryRecord>>

    //LiveData Observer
    fun getAllDataObserver(): MutableLiveData<List<DailyInventoryRecord>> {
        return allData
    }

    val listAllInventoryItems: LiveData<List<String>>

    //Retrieve instance of repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init{
        listAllInventoryItems = repository.readInventoryItemName
        allData = MutableLiveData()
        viewModelScope.launch {
            getAllData()
        }
    }

    // Function to retrieve all Data
    suspend fun getAllData(){
        val run = viewModelScope.launch {
            // Variable to hold all data retrieved
            val list = repository.readAllDailyInventoryRecordData()
            allData.postValue(list)
        }
        run.join()
    }

    //Function to delete a single record by giving that object as input to the function
    fun deleteDailyInventoryRecord(dailyInventoryRecord: DailyInventoryRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDailyInventoryRecord(dailyInventoryRecord)
            getAllData()
        }
    }

    fun addDailyInventoryRecord(dailyInventoryRecord: DailyInventoryRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDailyInventoryRecord(dailyInventoryRecord)
        }
    }

}