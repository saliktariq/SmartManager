package app.smartmanager.ui.foodsafetymanagement.equipmenttemperature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EquipmentTemperatureViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EquipmentTemperatureViewModel::class.java)){
            return EquipmentTemperatureViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}