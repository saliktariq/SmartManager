package app.smartmanager.ui.inventorymanagement.cookingrecord.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CookingRecordViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CookingRecordViewModel::class.java)){
            return CookingRecordViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}