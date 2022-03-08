package app.smartmanager.ui.setup.updatequery

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.smartmanager.R
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.setup.viewmodel.InventoryItemViewModel
import kotlin.properties.Delegates


class UpdateInventoryItemFragment : Fragment() {
    private lateinit var inventoryItemViewModel: InventoryItemViewModel
    private val args by navArgs<UpdateInventoryItemFragmentArgs>()

    //Variables to hold fragment data
    var id by Delegates.notNull<Long>()
    lateinit var name: String
    var supplier: String? = null
    var quantityPerUnit: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentView = inflater.inflate(R.layout.fragment_update_inventory_item, container, false)
        //Initialising the viewmodel
        inventoryItemViewModel = ViewModelProvider(this).get(InventoryItemViewModel::class.java)

        //Defining the spinner  variable to access spinner on the UI
        val relatedSupplierSpinner: AppCompatSpinner = fragmentView.findViewById<AppCompatSpinner>(R.id.relatedSupplier)

        //Arraylist to hold values of supplierlist
        var relatedSupplierListRetrieved = ArrayList<String?>()

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

        //Populating the fragment fields with received arguments
        fragmentView.findViewById<EditText>(R.id.productName).setText(args.currentRecord.name)
        fragmentView.findViewById<EditText>(R.id.quantityPerUnit).setText(args.currentRecord.quantityPerUnit.toString())

        val resourceID =  resources.getIdentifier(args.currentRecord.supplier,null,requireContext().packageName)
        fragmentView.findViewById<AppCompatSpinner>(R.id.relatedSupplier).setSelection(resourceID)

        val updateButton: Button = fragmentView.findViewById(R.id.btnUpdate)

        updateButton.setOnClickListener {
            //Setting the variables introduced earlier to hold updated data
            id = args.currentRecord.id
            name = fragmentView.findViewById<EditText>(R.id.productName).text.toString()
            supplier =  fragmentView.findViewById<AppCompatSpinner>(R.id.relatedSupplier).selectedItem.toString()
            quantityPerUnit = fragmentView.findViewById<EditText>(R.id.quantityPerUnit).toString().toInt()

            val updateData = inventoryItemViewModel.updateData(id,name,supplier,quantityPerUnit)

            if(updateData){
                findNavController().navigate(R.id.action_updateInventoryItemFragment_to_inventoryItemFragment)
            }

            //Adding menu to update cooked product item fragment
            setHasOptionsMenu(true)


        }


        return fragmentView
    }
    //Inflating the menu layout in supplier fragment
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //   super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_option, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.optionDelete){
            val deleteAlert = AlertDialog.Builder(requireContext())
            deleteAlert.setPositiveButton("Yes"){_, _ ->

                inventoryItemViewModel.deleteInventoryItemRecord(args.currentRecord)
                ToastMaker.showToast("${args.currentRecord.name} deleted!", GetAppContext.appContext)

                findNavController().navigate(R.id.action_updateInventoryItemFragment_to_inventoryItemFragment)

            }

            deleteAlert.setNegativeButton("No"){_, _ ->

            }
            deleteAlert.setTitle("Delete ${args.currentRecord.name}?")
            deleteAlert.setMessage("Please confirm that you want to delete ${args.currentRecord.name}")

            deleteAlert.create().show()

        }
        return super.onOptionsItemSelected(item)
    }
}