package app.smartmanager.ui.setup.insertquery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.constraintlayout.solver.widgets.Helper
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.smartmanager.R
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.ui.setup.viewmodel.InventoryItemViewModel


class AddInventoryItemFragment : Fragment() {
    private lateinit var inventoryItemViewModel: InventoryItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_add_inventory_item, container, false)

        //Initialising the viewmodel
        inventoryItemViewModel = ViewModelProvider(this).get(InventoryItemViewModel::class.java)

        //Defining the spinner  variable to access spinner on the UI
        val relatedSupplierSpinner: AppCompatSpinner = fragmentView.findViewById<AppCompatSpinner>(R.id.relatedSupplier)

        //Arraylist to hold values of supplierlist
        val relatedSupplierListRetrieved = ArrayList<String?>()

        //Setting arrayAdapter for relatedSupplierListRetrieved
        val chooseSupplierAdapter = ArrayAdapter<String>(
            this.requireActivity(), android.R.layout.simple_spinner_item,relatedSupplierListRetrieved
        )

        //Setting dropdown view resource for adapter
        chooseSupplierAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //Attaching adapter to spinner
        relatedSupplierSpinner.setAdapter(chooseSupplierAdapter)

        inventoryItemViewModel.readSupplierName.observe(viewLifecycleOwner){
            listOfSuppliers ->
            for (name in listOfSuppliers){
                relatedSupplierListRetrieved.add(name)
            }

            chooseSupplierAdapter.notifyDataSetChanged()
        }

        val addButton: Button = fragmentView.findViewById<Button>(R.id.btnAdd)

        addButton.setOnClickListener {
            val productName: String = fragmentView.findViewById<EditText>(R.id.productName).text.toString()
            var quantityPerUnit: Int = 0
            if(HelperFunctions.isNumber(fragmentView.findViewById<EditText>(R.id.quantityPerUnit).text.toString())){
                quantityPerUnit = fragmentView.findViewById<EditText>(R.id.quantityPerUnit).text.toString().toInt()
            }

            var relatedSupplier: String? = null
            if(fragmentView.findViewById<AppCompatSpinner>(R.id.relatedSupplier).selectedItem != null){
                relatedSupplier = fragmentView.findViewById<AppCompatSpinner>(R.id.relatedSupplier).selectedItem.toString()
            }


            val insertDate = inventoryItemViewModel.insertData(productName,relatedSupplier,quantityPerUnit)

            if(insertDate){
                findNavController().navigate(R.id.action_addInventoryItemFragment_to_inventoryItemFragment)
            }
        }

        return fragmentView
    }

}