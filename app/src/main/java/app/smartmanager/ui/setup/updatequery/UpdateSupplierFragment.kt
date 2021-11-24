package app.smartmanager.ui.setup.updatequery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.smartmanager.R
import app.smartmanager.ui.setup.viewmodel.SupplierViewModel
import kotlin.properties.Delegates

class UpdateSupplierFragment : Fragment() {

    private lateinit var supplierViewModel: SupplierViewModel
    private val args by navArgs<UpdateSupplierFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =  inflater.inflate(R.layout.fragment_update_supplier, container, false)

        // Initialising the supplierViewModel
        supplierViewModel = ViewModelProvider(this).get(SupplierViewModel::class.java)

        // Variables to hold fragment data
        var supplierID by Delegates.notNull<Long>()
        lateinit var supplierName: String //This property can not be null as per our entity definition
        var supplierEmail: String? = null
        var supplierPhone: String? = null
        var supplierAddress: String? = null

        //Populating the Fragment fields with received arguments
        fragmentView.findViewById<EditText>(R.id.supplierName).setText(args.currentSupplier.name).toString()
        fragmentView.findViewById<EditText>(R.id.supplierEmail).setText(args.currentSupplier.email).toString()
        fragmentView.findViewById<EditText>(R.id.supplierPhone).setText(args.currentSupplier.phone).toString()
        fragmentView.findViewById<EditText>(R.id.supplierAddress).setText(args.currentSupplier.address).toString()

        //Setting onClickListener for update button
        val updateButton: Button = fragmentView.findViewById(R.id.btnUpdate)

        updateButton.setOnClickListener {

            //Initialising fragment data variables with updated  entered data
            supplierID = args.currentSupplier.id.toLong()
            supplierName = fragmentView.findViewById<EditText>(R.id.supplierName).text.toString()
            supplierEmail = fragmentView.findViewById<EditText>(R.id.supplierEmail).text.toString()
            supplierPhone = fragmentView.findViewById<EditText>(R.id.supplierPhone).text.toString()
            supplierAddress = fragmentView.findViewById<EditText>(R.id.supplierAddress).text.toString()

            val updateData = supplierViewModel.updateDataSetToRoomDB(supplierID, supplierName, supplierEmail, supplierPhone, supplierAddress)

            if(updateData){
                //If insertData operation is successful navigate back to Supplier Fragment
                findNavController().navigate(R.id.action_updateSupplierFragment_to_supplierFragment)
            }

        }


        return fragmentView
    }


}