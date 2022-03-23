package app.smartmanager.ui.reports.viewmodel

import android.app.Application
import androidx.lifecycle.*
import app.smartmanager.datalayer.entity.*
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodSafetyReportsViewModel (application: Application) : AndroidViewModel(application){

    /*
    Variables to hold data for respective reports
     */
    lateinit var cleaningReportDaily: List<CleaningRecord>
    lateinit var cleaningReportWeekly: List<CleaningRecord>
    lateinit var cleaningReportMonthly: List<CleaningRecord>

    lateinit var probeCalibrationDaily: List<ProbeCalibrationRecord>
    lateinit var probeCalibrationWeekly: List<ProbeCalibrationRecord>
    lateinit var probelCalibrationMonthly: List<ProbeCalibrationRecord>

    lateinit var equipmentTemperatureDaily: List<EquipmentTemperatureRecord>
    lateinit var equipmentTemperatureWeekly: List<EquipmentTemperatureRecord>
    lateinit var equipmentTemperatureMonthly: List<EquipmentTemperatureRecord>

    lateinit var donenessTestDaily: List<CookedProductTemperatureRecord>
    lateinit var donenessTestWeekly: List<CookedProductTemperatureRecord>
    lateinit var donenessTestMonthly: List<CookedProductTemperatureRecord>


    //Retrieve instance of repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init{
        /*
        Using helper function getOldDate() to retrieve date object from x days ago
         */
        val weekAgo = HelperFunctions.getOldDate(7)
        val dayAgo = HelperFunctions.getOldDate(1)
        val monthAgo = HelperFunctions.getOldDate(30)

        //Initialising the variables in a coroutine
        viewModelScope.launch(Dispatchers.IO) {
            cleaningReportDaily = repository.cleaningReport(dayAgo)
            cleaningReportWeekly = repository.cleaningReport(weekAgo)
            cleaningReportMonthly = repository.cleaningReport(monthAgo)

            probeCalibrationDaily = repository.generateProbeCalibrationReport(dayAgo)
            probeCalibrationWeekly = repository.generateProbeCalibrationReport(weekAgo)
            probelCalibrationMonthly = repository.generateProbeCalibrationReport(monthAgo)

            equipmentTemperatureDaily = repository.generateEquipmentTemperatureReport(dayAgo)
            equipmentTemperatureWeekly = repository.generateEquipmentTemperatureReport(weekAgo)
            equipmentTemperatureMonthly = repository.generateEquipmentTemperatureReport(monthAgo)

            donenessTestDaily = repository.generateCookedProductTemperatureReport(dayAgo)
            donenessTestWeekly = repository.generateCookedProductTemperatureReport(weekAgo)
            donenessTestMonthly = repository.generateCookedProductTemperatureReport(monthAgo)



        }
    }

}