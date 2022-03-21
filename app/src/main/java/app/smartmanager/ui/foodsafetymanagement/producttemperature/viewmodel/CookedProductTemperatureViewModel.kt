package app.smartmanager.ui.foodsafetymanagement.producttemperature.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.CookedProductTemperatureRecord
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CookedProductTemperatureViewModel (application: Application) : AndroidViewModel(application){

    //Variable to hold Mutable LiveData retrieved from DB
    var allData: MutableLiveData<List<CookedProductTemperatureRecord>>

    //LiveData Observer
    fun getAllDataObserver(): MutableLiveData<List<CookedProductTemperatureRecord>> {
        return allData
    }

    val listAllCookedProduct: LiveData<List<String>>

    //Retrieve instance of repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init{
        listAllCookedProduct = repository.listAllCookedProductItem
        allData = MutableLiveData()
        viewModelScope.launch {
            getAllData()
        }
    }

    // Function to retrieve all Data
    suspend fun getAllData(){
        val run = viewModelScope.launch {
            // Variable to hold all data retrieved
            val list = repository.getAllCookedProductTemperatureRecordData()
            allData.postValue(list)
        }
        run.join()

    }

    //Function to delete a single record by giving that object as input to the function
    fun deleteCookedProductTemperatureRecord(cookedProductTemperatureRecord: CookedProductTemperatureRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCookedProductTemperatureRecord(cookedProductTemperatureRecord)
            getAllData()
        }
    }

    fun addCookedProductTemperatureRecord(cookedProductTemperatureRecord: CookedProductTemperatureRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCookedProductTemperatureRecord(cookedProductTemperatureRecord)
        }
    }
}