package app.smartmanager.ui.inventorymanagement.deliveryrecord.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DeliveryRecordViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveryRecordViewModel::class.java)){
            return DeliveryRecordViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}