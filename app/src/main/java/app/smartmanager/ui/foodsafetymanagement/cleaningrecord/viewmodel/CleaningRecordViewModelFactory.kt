package app.smartmanager.ui.foodsafetymanagement.cleaningrecord.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider



class CleaningRecordViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CleaningRecordViewModel::class.java)){
            return CleaningRecordViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}