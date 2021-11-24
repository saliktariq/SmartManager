package app.smartmanager.ui.setup.insertquery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.smartmanager.R
import app.smartmanager.ui.setup.viewmodel.SupplierViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

//import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddSupplierFragment : Fragment() {

    private lateinit var supplierViewModel: SupplierViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val fragmentView = inflater.inflate(R.layout.fragment_add_supplier, container, false)

        // Initialising the supplierViewModel

        supplierViewModel = ViewModelProvider(this).get(SupplierViewModel::class.java)

     //   val addButton = fragmentView.findViewById<FloatingActionButton>(R.id.btnAdd)

        val addButton: Button = fragmentView.findViewById(R.id.btnAdd)



        // Variables to hold fragment data
        lateinit var supplierName: String //This property can not be null as per our entity definition
        var supplierEmail: String? = null
        var supplierPhone: String? = null
        var supplierAddress: String? = null



// Attaching onClickListener to Add button (addButton)
        addButton.setOnClickListener {

            //Initialising fragment data variables with user entered data
            supplierName = fragmentView.findViewById<EditText>(R.id.supplierName).text.toString()
            supplierEmail = fragmentView.findViewById<EditText>(R.id.supplierEmail).text.toString()
            supplierPhone = fragmentView.findViewById<EditText>(R.id.supplierPhone).text.toString()
            supplierAddress = fragmentView.findViewById<EditText>(R.id.supplierAddress).text.toString()
            val insertData = supplierViewModel.insertDataSetToRoomDB(supplierName, supplierEmail, supplierPhone, supplierAddress)

            if(insertData){
                //If insertData operation is successful navigate back to Supplier Fragment
                findNavController().navigate(R.id.action_addSupplierFragment_to_supplierFragment)
            }

        }

        return fragmentView

    }



}
