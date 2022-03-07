package app.smartmanager.ui.setup.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.ChemicalListCOSHH
import app.smartmanager.datalayer.entity.Equipment
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChemicalListCOSHHViewModel(application: Application) : AndroidViewModel(application) {

    val readAllChemicalListCOSHHData: LiveData<List<ChemicalListCOSHH>>

    //Retrieve instance of SmartManager's Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init {
        //Initialising readAllChemicalListCOSHHData
        readAllChemicalListCOSHHData = repository.readAllChemicalListCOSHHData
    }

    fun addChemicalListCOSHH(chemicalListCOSHH: ChemicalListCOSHH){
        viewModelScope.launch(Dispatchers.IO)  {
            repository.addChemicalListCOSHH(chemicalListCOSHH)
        }
    }

    fun updateChemicalListCOSHH(chemicalListCOSHH: ChemicalListCOSHH){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateChemicalListCOSHH(chemicalListCOSHH)
        }
    }

    fun deleteChemicalListCOSHH(chemicalListCOSHH: ChemicalListCOSHH){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteChemicalListCOSHH(chemicalListCOSHH)
        }
    }

    fun insertData(name: String, purpose: String?, concentration: String?, notes: String?): Boolean{
        if(HelperFunctions.checkInputData(name)){
            //creating ChemicalListCOSHH object
            val chemicalListCOSHH = ChemicalListCOSHH(0,name, purpose,concentration,notes)

            //Calling ViewModel function to add ChemicalListCOSHH object to database
            addChemicalListCOSHH(chemicalListCOSHH)
            ToastMaker.showToast("Chemical added successfully", GetAppContext.appContext)
            return true
        } else {
            ToastMaker.showToast("Enter Chemical name", GetAppContext.appContext)
            return false
        }
    }

    fun updateData(chemicalListCOSHHID: Long, name: String, purpose: String?, concentration: String?, notes: String?): Boolean{
        if(HelperFunctions.checkInputData(name)){
            //creating ChemicalListCOSHH object
            val chemicalListCOSHH = ChemicalListCOSHH(chemicalListCOSHHID,name, purpose,concentration,notes)

            //Calling ViewModel function to update ChemicalListCOSHH object in database
            updateChemicalListCOSHH(chemicalListCOSHH)
            ToastMaker.showToast("Chemical updated successfully", GetAppContext.appContext)
            return true
        } else {
            ToastMaker.showToast("Enter Chemical name", GetAppContext.appContext)
            return false
        }
    }
}