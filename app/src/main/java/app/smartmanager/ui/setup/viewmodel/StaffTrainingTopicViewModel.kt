package app.smartmanager.ui.setup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.Probe
import app.smartmanager.datalayer.entity.StaffTrainingTopic
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.launch

class StaffTrainingTopicViewModel : ViewModel() {
    //Variable to hold Topic name
    var topicName: String = ""

    //Variable to hold error flags
    var topicNameAlreadyExist: Boolean = false

    //Retrieve instance of SmartManager's Repository
    val repository = SmartManagerRepo.get()

    //Variables to hold data received from repository
    var retrievedTopicName: String? = null

    //Variable to hold Mutable Live Data related to Probes stored in database
    var allTopics: MutableLiveData<List<StaffTrainingTopic>>

    // Topic data observer
    fun getAllTopicObserver(): MutableLiveData<List<StaffTrainingTopic>> {
        return allTopics
    }

    init {
        // initialising allTopics
        allTopics = MutableLiveData()

        viewModelScope.launch {
            getAllTopicData()
        }

    }

    //Function to insert Probe Data
    suspend fun insertTopicData(name: String): Boolean {
        if (name.length > 0) {
            val newTopicObject = StaffTrainingTopic(0, name.toString())
            val insertOperation = viewModelScope.launch {
                repository.addStaffTrainingTopic(newTopicObject)
            }
            insertOperation.join()
            val runUpdate = viewModelScope.launch {
                getAllTopicData()
            }
            runUpdate.join()

            ToastMaker.showToast("Training topic successfully added.", GetAppContext.appContext)
            return true
        } else {
            ToastMaker.showToast("Error! Enter a valid topic name.", GetAppContext.appContext)
            return false
        }
    }


    // Function to retrieve all Topic Data
    suspend fun getAllTopicData() {

            // Variable to hold all topic data retrieved
            val list = repository.readStaffTrainingTopicForUnitTests()
            allTopics.postValue(list)


    }


    // Function to delete a topic dataset by giving StaffTrainingTopic object
    suspend fun deleteStaffTrainingTopic(staffTrainingTopic: StaffTrainingTopic) {
        val runDelete = viewModelScope.launch {
            repository.deleteStaffTrainingTopic(staffTrainingTopic)
        }
        runDelete.join()
        val runUpdate = viewModelScope.launch {
            getAllTopicData()
        }
        runUpdate.join()

    }

}