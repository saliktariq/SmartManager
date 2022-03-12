package app.smartmanager.ui.inventorymanagement.foodwastagerecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.FoodWasteRecord
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodWasteRecordViewModel(application: Application) : AndroidViewModel(application){

    //Variable to hold Mutable LiveData retrieved from DB
    var allData: MutableLiveData<List<FoodWasteRecord>>

    //LiveData Observer
    fun getAllDataObserver(): MutableLiveData<List<FoodWasteRecord>> {
        return allData
    }

    val listAllFoodItems: LiveData<List<String>>

    //Retrieve instance of repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init{
        listAllFoodItems = repository.listAllCookedProductItem
        allData = MutableLiveData()
        viewModelScope.launch {
            getAllData()
        }
    }

    // Function to retrieve all Data
    suspend fun getAllData(){
        val run = viewModelScope.launch {
            // Variable to hold all data retrieved
            val list = repository.readAllFoodWasteRecordData
            allData.postValue(list)
        }
        run.join()
    }

    //Function to delete a single record by giving that object as input to the function
    fun deleteCleaningRecord(foodWasteRecord: FoodWasteRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFoodWasteRecord(foodWasteRecord)
            getAllData()
        }
    }

    fun addFoodWasteRecord(foodWasteRecord: FoodWasteRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFoodWasteRecord(foodWasteRecord)
        }
    }

}