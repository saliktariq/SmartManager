package app.smartmanager.ui.setup.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.CookedProductItem
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CookedProductItemViewModel(application: Application) : AndroidViewModel(application) {

    val readAllCookedProductItemRecordData: LiveData<List<CookedProductItem>>
    val readInventoryItemName: LiveData<List<String>>

    //Retrieve instance of SmartManager's Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init {
        //Initialising readAllCookedProductItemData and readInventoryItemName
        readAllCookedProductItemRecordData = repository.readAllCookedProductItemRecordData
        readInventoryItemName = repository.readInventoryItemName
    }

    fun addCookedProductItemRecord(record: CookedProductItem) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addCookedProductItemRecord(record)

        }

    }

    fun updateCookedProductItemRecord(record: CookedProductItem) {
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCookedProductItemRecord(record)

        }
    }

    fun deleteCookedProductItemRecord(record: CookedProductItem) {
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCookedProductItemRecord(record)

        }
    }

    fun insertData(
        name: String,
        quantityPerCookingBatch: Int?,
        relatedProduct: String?
    ): Boolean {
        if (HelperFunctions.checkInputData(name)) {
            //creating CookedProductItem object
            val cookedProductItem =
                CookedProductItem(0, name, quantityPerCookingBatch, relatedProduct)

            //Calling ViewModel function to add CookedProductItem object to the database
            addCookedProductItemRecord(cookedProductItem)
            ToastMaker.showToast("Cooked Product item added successfully", GetAppContext.appContext)
            return true
        } else {
            ToastMaker.showToast("Enter cooked product item name", GetAppContext.appContext)
            return false
        }
    }

        fun updateData(
            id: Long,
            name: String,
            quantityPerCookingBatch: Int?,
            relatedProduct: String?
        ): Boolean{
            if(HelperFunctions.checkInputData(name)){
                //creating CookedProductItem object
                val cookedProductItem = CookedProductItem(id,name, quantityPerCookingBatch,relatedProduct)

                //Calling ViewModel function to update CookedProductItem object in the database
                updateCookedProductItemRecord(cookedProductItem)
                ToastMaker.showToast("Cooked Product item updated successfully", GetAppContext.appContext)
                return true
            } else {
                ToastMaker.showToast("Enter cooked product item name", GetAppContext.appContext)
                return false
            }

    }
}