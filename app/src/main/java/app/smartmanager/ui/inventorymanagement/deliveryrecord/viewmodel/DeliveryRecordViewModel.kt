package app.smartmanager.ui.inventorymanagement.deliveryrecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.datalayer.entity.DeliveryRecord
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeliveryRecordViewModel(application: Application) : AndroidViewModel(application){

    //Variable to hold Mutable LiveData retrieved from DB
    var allData: MutableLiveData<List<DeliveryRecord>>

    //LiveData Observer
    fun getAllDataObserver(): MutableLiveData<List<DeliveryRecord>> {
        return allData
    }

    val listAllProducts: LiveData<List<String>>
    val listAllSuppliers: LiveData<List<String>>

    //Retrieve instance of repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init{
        listAllProducts = repository.readInventoryItemName
        listAllSuppliers = repository.readSupplierName
        allData = MutableLiveData()
        viewModelScope.launch {
            getAllData()
        }
    }

    // Function to retrieve all Data
    suspend fun getAllData(){
        val run = viewModelScope.launch {
            // Variable to hold all data retrieved
            val list = repository.readAllDeliveryRecordData
            allData.postValue(list)
        }
        run.join()
    }

    //Function to delete a single record by giving that object as input to the function
    fun deleteDeliveryRecord(deliveryRecord: DeliveryRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDeliveryRecord(deliveryRecord)
            getAllData()
        }
    }

    fun addDeliveryRecord(deliveryRecord: DeliveryRecord){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDeliveryRecord(deliveryRecord)
        }
    }
}