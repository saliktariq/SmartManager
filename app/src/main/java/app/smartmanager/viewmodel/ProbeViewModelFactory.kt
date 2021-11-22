package app.smartmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProbeViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProbeViewModel::class.java)){
            return ProbeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}