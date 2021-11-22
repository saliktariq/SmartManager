package app.smartmanager.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.Probe
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.launch

class ProbeViewModel : ViewModel() {

    //Variable to hold Probe Fragment form data
    var probeName: String = ""

    //Variable to hold error flags
    var probeNameAlreadyExist: Boolean = false

    //Retrieve instance of SmartManager's Repository
    val repository = SmartManagerRepo.get()

    //Variables to hold data received from repository
    var retrievedProbeName: String? = null

    //Variable to hold Mutable Live Data related to Probes stored in database
    var allProbes: MutableLiveData<List<Probe>>

    // Probe data observer
    fun getAllProbesObserver(): MutableLiveData<List<Probe>> {
        return allProbes
    }

    init {
        // initialising allProbes
        allProbes = MutableLiveData()

        viewModelScope.launch {
            getAllProbeData()
        }

    }

    //Function to insert Probe Data
    suspend fun insertProbeData(name: String): Boolean{
        var probeData: Probe? = null

        if(name.length > 1){
            val runQuery = viewModelScope.launch {
                probeData = repository.getProbeByName(name)
            }
            runQuery.join()

            if (probeData != null){
                retrievedProbeName = probeData!!.probeName
            }

            if(retrievedProbeName.equals(probeName))
            {
                probeNameAlreadyExist = true

                //    ToastMaker.showToast("Probe name already exists.", GetAppContext.appContext)
                return false
            } else {
                val newProbeObject = Probe(name)
                val insertOperation =  viewModelScope.launch {
                    repository.insertProbeData(newProbeObject)
                }
                insertOperation.join()
                val runUpdate = viewModelScope.launch {
                    getAllProbeData()
                }
                runUpdate.join()

                ToastMaker.showToast("Probe successfully added.", GetAppContext.appContext)
                return true
            }
        }

        ToastMaker.showToast("Error! Enter a valid probe name.", GetAppContext.appContext)
        return false
    }

    // Function to retrieve all Probe Data
    suspend fun getAllProbeData(){
        val run = viewModelScope.launch {
            // Variable to hold all probe data retrieved
            val list = repository.getAllProbeData()
            allProbes.postValue(list)
        }
        run.join()

    }


    // Function to delete a probe dataset by giving probeName
    suspend fun deleteProbeByName(probeName: String){
        val runDelete = viewModelScope.launch {
            repository.deleteProbeByName(probeName)

        }
        runDelete.join()
        val runUpdate = viewModelScope.launch {
            getAllProbeData()
        }
        runUpdate.join()

    }


    // Function to delete a probe dataset by giving probe object
    suspend fun deleteProbeByProbeObject(probe: Probe){
        val runDelete = viewModelScope.launch {
            repository.deleteProbeByProbeObject(probe)

        }
        runDelete.join()
        val runUpdate = viewModelScope.launch {
            getAllProbeData()
        }
        runUpdate.join()

    }







}