package app.smartmanager.ui.setup.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.ChemicalListCOSHH
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChemicalListCOSHHViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllChemicalListCOSHHData: LiveData<List<ChemicalListCOSHH>>

    //Retrieve instance of SmartManager's Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init {
        //Initialising readAllSupplierData
        readAllChemicalListCOSHHData = repository.readAllChemicalListCOSHHData
    }

    fun aaddChemicalListCOSHH(chemicalListCOSHH: ChemicalListCOSHH){
        viewModelScope.launch(Dispatchers.IO)  {
            repository.addChemicalListCOSHH(chemicalListCOSHH)
        }
    }
}