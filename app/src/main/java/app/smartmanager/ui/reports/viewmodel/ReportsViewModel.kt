package app.smartmanager.ui.reports.viewmodel

import android.app.Application
import androidx.lifecycle.*
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReportsViewModel (application: Application) : AndroidViewModel(application){

    //Variable to hold Mutable LiveData retrieved from DB
//    val cleaningReportWeekly: LiveData<List<CleaningRecord>>
    lateinit var cleaningReportDaily: List<CleaningRecord>
    lateinit var cleaningReportWeekly: List<CleaningRecord>
    lateinit var cleaningReportMonthly: List<CleaningRecord>



    //Retrieve instance of repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init{
        val weekAgo = HelperFunctions.getOldDate(7)
        val dayAgo = HelperFunctions.getOldDate(1)
        val monthAgo = HelperFunctions.getOldDate(30)
        viewModelScope.launch(Dispatchers.IO) {
            cleaningReportDaily = repository.cleaningReport(dayAgo)
            cleaningReportWeekly = repository.cleaningReport(weekAgo)
            cleaningReportMonthly = repository.cleaningReport(monthAgo)
        }
    }

}