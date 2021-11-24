package app.smartmanager.ui.setup.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.smartmanager.datalayer.entity.Supplier
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.repository.SmartManagerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SupplierViewModel(application: Application) : AndroidViewModel(application) {

    val readAllSupplierData: LiveData<List<Supplier>>







    //Retrieve instance of SmartManager's Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    init {
        //Initialising readAllSupplierData
        readAllSupplierData = repository.readAllSupplierData
    }

    fun addSupplier(supplier: Supplier){
        viewModelScope.launch(Dispatchers.IO)  {
            repository.addSupplier(supplier)
        }
    }

    fun insertDataSetToRoomDB(supplierName: String, supplierEmail: String?, supplierPhone: String?, supplierAddress: String?): Boolean {

        if(checkInputData(supplierName)){
            //Create a Supplier object
            val supplier = Supplier(0,supplierName,supplierEmail,supplierPhone,supplierAddress)

            //Calling ViewModel function to add supplier object to Database
            addSupplier(supplier)

            //Inform UI of successful entry
            ToastMaker.showToast("Supplier added successfully!", GetAppContext.appContext)
            return true
        } else {
            //In case the input check fails
            ToastMaker.showToast("Enter supplier name", GetAppContext.appContext)
            return false
        }




    }

    /*
    Function to check if not-null fields are not empty
     */
    private fun checkInputData(supplierName: String): Boolean{
        return !(TextUtils.isEmpty(supplierName))
    }
}