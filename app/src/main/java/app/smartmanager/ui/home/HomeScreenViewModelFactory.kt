package app.smartmanager.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeScreenViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)){
            return HomeScreenViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}