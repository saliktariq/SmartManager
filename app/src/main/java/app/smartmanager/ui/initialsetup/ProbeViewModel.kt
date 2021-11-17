package app.smartmanager.ui.initialsetup

import androidx.lifecycle.ViewModel
import app.smartmanager.datalayer.entity.Probe
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.repository.SmartManagerRepo

class ProbeViewModel : ViewModel() {

    //Variable to hold Probe Fragment form data
    var probeName: String = ""

    //Variable to hold error flags
    var probeNameAlreadyExist: Boolean = false

    //Retrieve instance of SmartManager's Repository
    val repository = SmartManagerRepo.get()

    //Variables to hold data received from repository
    var retrievedProbeName: String? = null

    //Function to insert Probe Data
    suspend fun insertProbeData(name: String): Boolean{
        if(name.length > 1){
            val probeData = repository.getProbeByName(name)
            if (probeData != null){
                retrievedProbeName = probeData.probeName
            }

            if(retrievedProbeName.equals(probeName))
            {
                probeNameAlreadyExist = true

                ToastMaker.showToast("Probe name already exists.", GetAppContext.appContext)
                return false
            } else {
                val newProbeObject = Probe(0,name)
                repository.insertProbeData(newProbeObject)

                ToastMaker.showToast("Probe successfully added.", GetAppContext.appContext)
                return true
            }
        }

        ToastMaker.showToast("Error! Enter a valid probe name.", GetAppContext.appContext)
        return false
    }

}