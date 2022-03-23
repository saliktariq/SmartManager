package app.smartmanager.ui.inventorymanagement.cookingrecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.datalayer.entity.CookingRecord
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CookingRecordViewModel (application: Application) : AndroidViewModel(application){

    //Variable to hold Mutable LiveData retrieved from DB
    var allData: MutableLiveData<List<CookingRecord>>

    //LiveData Observer
    fun getAllDataObserver(): MutableLiveData<List<CookingRecord>> {
        return allData
    }

    val listAllCookedProductItem: LiveData<List<String>>

    //Retrieve instance of repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init{
        listAllCookedProductItem = repository.listAllCookedProductItem
        allData = MutableLiveData()
        viewModelScope.launch {
            getAllData()
        }
    }

    // Function to retrieve all Data
    suspend fun getAllData(){
        val run = viewModelScope.launch {
            // Variable to hold all data retrieved
            val list = repository.readAllCookingRecordData()
            allData.postValue(list)
        }
        run.join() //Only proceed once run is completed
    }


    //Function to delete a single record by giving that object as input to the function
    fun deleteCookingRecord(cookingRecord: CookingRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCookingRecord(cookingRecord)
            getAllData()
        }
    }

    //Function to insert data

    fun addCookingRecord(cookingRecord: CookingRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCookingRecord(cookingRecord)
        }
    }
}