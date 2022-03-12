package app.smartmanager.ui.inventorymanagement.dailyinventory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DailyInventoryViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyInventoryViewModel::class.java)){
            return DailyInventoryViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}