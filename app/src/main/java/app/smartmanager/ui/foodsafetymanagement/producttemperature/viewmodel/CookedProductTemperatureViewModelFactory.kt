package app.smartmanager.ui.foodsafetymanagement.producttemperature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CookedProductTemperatureViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CookedProductTemperatureViewModel::class.java)){
            return CookedProductTemperatureViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}