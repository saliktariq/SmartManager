package app.smartmanager.ui.foodsafetymanagement.equipmenttemperature.viewmodel

import android.app.Application
import androidx.lifecycle.*
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.datalayer.entity.EquipmentTemperatureRecord
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EquipmentTemperatureViewModel (application: Application) : AndroidViewModel(application){
    //Variable to hold Mutable LiveData retrieved from DB
    var allData: MutableLiveData<List<EquipmentTemperatureRecord>>

    //LiveData Observer
    fun getAllDataObserver(): MutableLiveData<List<EquipmentTemperatureRecord>> {
        return allData
    }

    val listAllEquipment: LiveData<List<String>>

    //Retrieve instance of repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init{
        listAllEquipment = repository.listAllEquipment
        allData = MutableLiveData()
        viewModelScope.launch {
            getAllData()
        }
    }

    // Function to retrieve all Data
    suspend fun getAllData(){
        val run = viewModelScope.launch {
            // Variable to hold all data retrieved
            val list = repository.getAllEquipmentTemperatureRecordData()
            allData.postValue(list)
        }
        run.join()

    }

    //Function to delete a single record by giving that object as input to the function
    fun deleteCleaningRecord(equipmentTemperatureRecord: EquipmentTemperatureRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEquipmentTemperatureRecord(equipmentTemperatureRecord)
            getAllData()
        }
    }

    fun addEquipmentTemperatureRecord(equipmentTemperatureRecord: EquipmentTemperatureRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEquipmentTemperatureRecord(equipmentTemperatureRecord)
        }
    }
}