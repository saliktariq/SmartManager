package app.smartmanager.ui.setup.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.Equipment
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EquipmentViewModel(application: Application) : AndroidViewModel(application) {

    val readAllEquipmentData: LiveData<List<Equipment>>

    //Retrieve instance of SmartManager's Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init {
        //Initialising readAllSupplierData
        readAllEquipmentData = repository.readAllEquipmentData
    }

    fun addEquipment(equipment: Equipment){
        viewModelScope.launch(Dispatchers.IO)  {
            repository.addEquipment(equipment)
        }
    }

    fun updateEquipment(equipment: Equipment){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEquipment(equipment)
        }
    }

    fun deleteEquipment(equipment: Equipment){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteEquipment(equipment)
        }
    }

    fun insertData(name: String, temperatureControl: Boolean?): Boolean{
        if(HelperFunctions.checkInputData(name)){
            //creating Equipment object
            val equipment = Equipment(0,name, temperatureControl)

            //Calling ViewModel function to add equipment object to database
            addEquipment(equipment)

            ToastMaker.showToast("Equipment added successfully", GetAppContext.appContext)
            return true
        } else {
            ToastMaker.showToast("Enter Equipment name", GetAppContext.appContext)
            return false
        }
    }

    fun updateData(equipmentID: Long, name: String, temperatureControl: Boolean?): Boolean {
        if(HelperFunctions.checkInputData(name)){
            val equipment = Equipment(equipmentID, name, temperatureControl)
            updateEquipment(equipment)
            ToastMaker.showToast("Equipment updated successfully", GetAppContext.appContext)
            return true

        } else {
            ToastMaker.showToast("Enter valid Equipment name", GetAppContext.appContext)
            return false
        }
    }
}