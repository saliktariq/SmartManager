package app.smartmanager.ui.reports.viewmodel

import android.app.Application
import androidx.lifecycle.*
import app.smartmanager.datalayer.entity.*
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InventoryReportsViewModel (application: Application) : AndroidViewModel(application){

    /*
    Variables to hold data for respective reports
     */
    lateinit var cookingReportDaily: List<CookingRecord>
    lateinit var cookingReportWeekly: List<CookingRecord>
    lateinit var cookingReportMonthly: List<CookingRecord>

    lateinit var inventoryReportDaily: List<DailyInventoryRecord>
    lateinit var inventoryReportWeekly: List<DailyInventoryRecord>
    lateinit var inventoryReportMonthly: List<DailyInventoryRecord>

    lateinit var deliveryReportDaily: List<DeliveryRecord>
    lateinit var deliveryReportWeekly: List<DeliveryRecord>
    lateinit var deliveryReportMonthly: List<DeliveryRecord>

    lateinit var foodWasteReportDaily: List<FoodWasteRecord>
    lateinit var foodWasteReportWeekly: List<FoodWasteRecord>
    lateinit var foodWasteReportMonthly: List<FoodWasteRecord>



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
            cookingReportDaily = repository.generateCookingRecordReport(dayAgo)
            cookingReportWeekly = repository.generateCookingRecordReport(weekAgo)
            cookingReportMonthly = repository.generateCookingRecordReport(monthAgo)

            inventoryReportDaily = repository.generateInventoryReport(dayAgo)
            inventoryReportWeekly = repository.generateInventoryReport(weekAgo)
            inventoryReportMonthly = repository.generateInventoryReport(monthAgo)

            deliveryReportDaily = repository.generateDeliveryRecordReport(dayAgo)
            deliveryReportWeekly = repository.generateDeliveryRecordReport(weekAgo)
            deliveryReportMonthly = repository.generateDeliveryRecordReport(monthAgo)

            foodWasteReportDaily = repository.generateFoodWasteReport(dayAgo)
            foodWasteReportWeekly = repository.generateFoodWasteReport(weekAgo)
            foodWasteReportMonthly = repository.generateFoodWasteReport(monthAgo)



        }
    }

}