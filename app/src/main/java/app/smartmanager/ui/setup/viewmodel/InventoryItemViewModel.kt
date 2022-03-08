package app.smartmanager.ui.setup.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.InventoryItem
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InventoryItemViewModel(application: Application) : AndroidViewModel(application) {

    val readAllInventoryItemRecordData: LiveData<List<InventoryItem>>
    val readSupplierName: LiveData<List<String>>

    //Retrieve instance of SmartManager's Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init {
        //Initialising readAllCookedProductItemData
        readAllInventoryItemRecordData = repository.readAllInventoryItemRecordData
        readSupplierName = repository.readSupplierName
    }

    fun addInventoryItemRecord(record: InventoryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addInventoryItemRecord(record)

        }

    }

    fun updateInventoryItemRecord(record: InventoryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateInventoryItemRecord(record)

        }
    }

    fun deleteInventoryItemRecord(record: InventoryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteInventoryItemRecord(record)

        }
    }

    fun insertData(
        name: String,
        supplier: String?,
        quantityPerUnit: Int?
    ): Boolean {
        if(HelperFunctions.checkInputData(name)){
            //creating InventoryItem object
            val inventoryItem = InventoryItem(0,name,supplier,quantityPerUnit)

            //calling viewModel function addInventoryItemRecord to insert data
            addInventoryItemRecord(inventoryItem)
            ToastMaker.showToast("Inventory item added successfully", GetAppContext.appContext)
            return true
        } else {
            ToastMaker.showToast("Enter item name", GetAppContext.appContext)
            return false
        }
    }

    fun updateData(
        id: Long,
        name: String,
        supplier: String?,
        quantityPerUnit: Int?
    ): Boolean {
        if(HelperFunctions.checkInputData(name)){
            //creating InventoryItem object
            val inventoryItem = InventoryItem(id,name,supplier,quantityPerUnit)

            //calling viewModel function addInventoryItemRecord to update data
            updateInventoryItemRecord(inventoryItem)
            ToastMaker.showToast("Inventory item updated successfully", GetAppContext.appContext)
            return true
        } else {
            ToastMaker.showToast("Enter item name", GetAppContext.appContext)
            return false
        }
    }
}