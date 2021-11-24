package app.smartmanager.ui.setup.updatequery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import app.smartmanager.R
import app.smartmanager.ui.setup.viewmodel.SupplierViewModel

class UpdateSupplierFragment : Fragment() {

    private lateinit var supplierViewModel: SupplierViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =  inflater.inflate(R.layout.fragment_update_supplier, container, false)

        // Initialising the supplierViewModel
        supplierViewModel = ViewModelProvider(this).get(SupplierViewModel::class.java)


        return fragmentView
    }


}