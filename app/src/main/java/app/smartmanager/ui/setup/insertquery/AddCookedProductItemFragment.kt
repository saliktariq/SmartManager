package app.smartmanager.ui.setup.insertquery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.util.*
import app.smartmanager.R
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.ui.setup.viewmodel.CookedProductItemViewModel


class AddCookedProductItemFragment : Fragment() {

    private lateinit var cookedProductItemViewModel: CookedProductItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_add_cooked_product_item, container, false)


        //Initialising the cookedProductItemViewModel
        cookedProductItemViewModel = ViewModelProvider(this).get(CookedProductItemViewModel::class.java)

        //Defining spinner variable to access spinner on the UI
        val relatedProductSpinner: AppCompatSpinner= fragmentView.findViewById<AppCompatSpinner>(R.id.relatedProduct)

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

        val addButton: Button = fragmentView.findViewById(R.id.btnAdd)

        addButton.setOnClickListener {
            val cookedProductName: String = fragmentView.findViewById<EditText>(R.id.cookedProductName).text.toString()
            var quantityPerCookingBatch: Int = 0
            if (fragmentView.findViewById<EditText>(R.id.quantityPerCookingBatch).text.toString()
                    .isNotEmpty()
            ){ if(HelperFunctions.isNumber(fragmentView.findViewById<EditText>(R.id.quantityPerCookingBatch).text.toString())){
                quantityPerCookingBatch = fragmentView.findViewById<EditText>(R.id.quantityPerCookingBatch).text.toString().toInt()
            }

            }
            var relatedProduct: String? = null
            if (fragmentView.findViewById<AppCompatSpinner>(R.id.relatedProduct).selectedItem.toString()
                    .isNotEmpty()
            ){
                    relatedProduct = fragmentView.findViewById<AppCompatSpinner>(R.id.relatedProduct).selectedItem.toString()


            }

            val insertData = cookedProductItemViewModel.insertData(cookedProductName, quantityPerCookingBatch,relatedProduct)

            if(insertData){
                findNavController().navigate(R.id.action_addCookedProductItemFragment_to_cookedProductItemFragment)
            }

        }



        return fragmentView
    }

}