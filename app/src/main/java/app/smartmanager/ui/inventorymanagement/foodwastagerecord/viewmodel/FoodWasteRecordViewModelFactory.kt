package app.smartmanager.ui.inventorymanagement.foodwastagerecord.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FoodWasteRecordViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodWasteRecordViewModel::class.java)){
            return FoodWasteRecordViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}