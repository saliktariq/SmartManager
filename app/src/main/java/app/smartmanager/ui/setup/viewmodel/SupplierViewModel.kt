package app.smartmanager.ui.setup.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.Supplier
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SupplierViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllSupplierData: LiveData<List<Supplier>>

    //Retrieve instance of SmartManager's Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init {
        //Initialising readAllSupplierData
        readAllSupplierData = repository.readAllSupplierData
    }

    fun addSupplier(supplier: Supplier){
        viewModelScope.launch(Dispatchers.IO)  {
            repository.addSupplier(supplier)
        }
    }
}