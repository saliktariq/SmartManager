package app.smartmanager.ui.setup.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.Equipment
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EquipmentViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllEquipmentData: LiveData<List<Equipment>>

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
}