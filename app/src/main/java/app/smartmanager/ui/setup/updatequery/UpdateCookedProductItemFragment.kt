package app.smartmanager.ui.setup.updatequery

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.smartmanager.R
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.setup.viewmodel.CookedProductItemViewModel
import java.util.ArrayList
import kotlin.properties.Delegates


class UpdateCookedProductItemFragment : Fragment() {
    private lateinit var cookedProductItemViewModel: CookedProductItemViewModel
    private val args by navArgs<UpdateCookedProductItemFragmentArgs>()

    //Variables to hold fragment data
    var id by Delegates.notNull<Long>()
    lateinit var name: String
    var quantityPerCookingBatch : Int? = 0
    var relatedInventoryItem: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment

        val fragmentView = inflater.inflate(R.layout.fragment_update_cooked_product_item, container, false)

        //Initialising the cookedProductItemViewModel
        cookedProductItemViewModel = ViewModelProvider(this).get(CookedProductItemViewModel::class.java)

        //Defining spinner variable to access spinner on the UI
        val relatedProductSpinner: AppCompatSpinner = fragmentView.findViewById<AppCompatSpinner>(R.id.relatedProduct)

        //Arraylist to hold values of related inventory (product) items from the DB
        var relatedProductInventoryItemsRetrieved = ArrayList<String?>()

        //Setting arrayAdapter for relatedProductSpinner
        val chooseProductItemAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item, relatedProductInventoryItemsRetrieved
        )

        //Setting dropdown view resource to adapter
        chooseProductItemAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //Attaching adapter to spinner
        relatedProductSpinner.setAdapter(chooseProductItemAdapter)

        cookedProductItemViewModel.readInventoryItemName.observe(viewLifecycleOwner){
                listOfInventoryItems ->
            for (name in listOfInventoryItems){
                relatedProductInventoryItemsRetrieved.add(name)
            }
            chooseProductItemAdapter.notifyDataSetChanged()
        }


        //Populating the Fragment fields with received arguments
        fragmentView.findViewById<EditText>(R.id.updateCookedProductName).setText(args.currentRecord.name)
        args.currentRecord.quantityPerCookingBatch?.let {
            fragmentView.findViewById<EditText>(R.id.updateQuantityPerCookingBatch).setText(
                it
            )
        }

        val resourceID =  resources.getIdentifier(args.currentRecord.relatedInventoryItem,null,requireContext().packageName)

        fragmentView.findViewById<AppCompatSpinner>(R.id.updateRelatedProduct).setSelection(resourceID)

        val updateButton: Button = fragmentView.findViewById<Button>(R.id.btnUpdate)

        updateButton.setOnClickListener {
            //Setting the variables introduced to hold updated data
            id = args.currentRecord.id
            name = fragmentView.findViewById<EditText>(R.id.updateCookedProductName).text.toString()
            quantityPerCookingBatch = fragmentView.findViewById<EditText>(R.id.updateQuantityPerCookingBatch).toString().toInt()
            relatedInventoryItem = fragmentView.findViewById<AppCompatSpinner>(R.id.updateRelatedProduct).selectedItem.toString()

            val updateData = cookedProductItemViewModel.updateData(id,name,quantityPerCookingBatch,relatedInventoryItem)
            if(updateData){
                findNavController().navigate(R.id.action_updateCookedProductItemFragment_to_cookedProductItemFragment)
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

                cookedProductItemViewModel.deleteCookedProductItemRecord(args.currentRecord)
                ToastMaker.showToast("${args.currentRecord.name} deleted!", GetAppContext.appContext)

                findNavController().navigate(R.id.action_updateCookedProductItemFragment_to_cookedProductItemFragment)

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