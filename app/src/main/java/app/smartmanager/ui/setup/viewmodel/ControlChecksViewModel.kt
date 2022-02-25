package app.smartmanager.ui.setup.viewmodel

import android.app.Application
import android.service.controls.Control
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.ControlChecks
import app.smartmanager.datalayer.entity.Equipment
import app.smartmanager.datalayer.entity.Supplier
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ControlChecksViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllControlChecksData: LiveData<List<ControlChecks>>

    //Retrieve instance of SmartManager's Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init {
        //Initialising readAllSupplierData
        readAllControlChecksData = repository.readAllControlChecksData
    }

    fun addControlChecks(controlChecks: ControlChecks){
        viewModelScope.launch(Dispatchers.IO)  {
            repository.addControlChecks(controlChecks)
        }
    }

    fun updateControlChecks(controlChecks: ControlChecks){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateControlChecks(controlChecks)
        }
    }

    fun deleteControlChecks(controlChecks: ControlChecks){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteControlChecks(controlChecks)
        }
    }

    fun insertData(name: String, description: String?): Boolean {

        if(HelperFunctions.checkInputData(name)){
            //creating ControlCheck object
            val controlCheck = ControlChecks(0,name, description)

            //Calling ViewModel function to add controlCheck object to database
            addControlChecks(controlCheck)

            ToastMaker.showToast("Control Check added successfully", GetAppContext.appContext)
            return true
        } else {
            ToastMaker.showToast("Enter Control Check name", GetAppContext.appContext)
            return false
        }

    }

    fun updateData(controlCheckID: Long, name: String, description: String?): Boolean {
        if(HelperFunctions.checkInputData(name)){
            val controlCheck = ControlChecks(controlCheckID,name, description)
            updateControlChecks(controlCheck)
            ToastMaker.showToast("Control Check updated successfully", GetAppContext.appContext)
            return true

        } else {
            ToastMaker.showToast("Enter valid Control Check name", GetAppContext.appContext)
            return false
        }
    }


}