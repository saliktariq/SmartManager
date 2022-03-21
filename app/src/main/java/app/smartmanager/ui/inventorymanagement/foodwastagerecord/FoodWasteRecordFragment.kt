package app.smartmanager.ui.inventorymanagement.foodwastagerecord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatSpinner
import androidx.constraintlayout.solver.widgets.Helper
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.CleaningRecordAdapter
import app.smartmanager.adapters.FoodWasteRecordAdapter
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.datalayer.entity.FoodWasteRecord
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.foodsafetymanagement.cleaningrecord.viewmodel.CleaningRecordViewModel
import app.smartmanager.ui.inventorymanagement.foodwastagerecord.viewmodel.FoodWasteRecordViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import java.util.*

class FoodWasteRecordFragment : Fragment(), FoodWasteRecordAdapter.FoodWasteRecordClickListenter {

    private lateinit var foodWasteRecordList: RecyclerView
    lateinit var recyclerViewAdapter: FoodWasteRecordAdapter
    private lateinit var foodWasteRecordViewModel: FoodWasteRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.food_waste_record_fragment, container, false)

        //Initialising the recycleView list
        foodWasteRecordList = fragmentView.findViewById(R.id.showDatalayout)

        //Setting layout for recycleView list
        foodWasteRecordList.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = FoodWasteRecordAdapter(this@FoodWasteRecordFragment)
            adapter = recyclerViewAdapter

            val dividerItemDecoration = DividerItemDecoration(
                foodWasteRecordList.getContext(),
                LinearLayoutManager(activity).getOrientation()
            )
            addItemDecoration(dividerItemDecoration)
        }

        //Defining spinner variables to access spinners on the UI
        val cookedProductSpinner: AppCompatSpinner =
            fragmentView.findViewById<AppCompatSpinner>(R.id.cookedProduct)

        //Arraylist to hold values retrieved from DB for spinner
        var cookedProductNameDataRetrieved = ArrayList<String>()

        //Setting ArrayAdapter for Spinner
        val cookedProductAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item, cookedProductNameDataRetrieved
        )

        //Setting drop down view resource to adapter
        cookedProductAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //attaching adapter to spinner
        cookedProductSpinner.setAdapter(cookedProductAdapter)

        foodWasteRecordViewModel  = ViewModelProvider(this).get(FoodWasteRecordViewModel::class.java)

        foodWasteRecordViewModel.listAllFoodItems.observe(viewLifecycleOwner) { listOfItems ->
            for (name in listOfItems) {
                cookedProductNameDataRetrieved.add(name.toString())
            }
            cookedProductAdapter.notifyDataSetChanged()

            if (listOfItems.isEmpty()){
                ToastMaker.showToast("Add Cooked Food item SETUP before you can add Food Wastage record", GetAppContext.appContext)
                findNavController().navigate(R.id.action_foodWasteRecordFragment_to_cookedProductItemFragment)
            }
        }

        foodWasteRecordViewModel.getAllDataObserver().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setFoodWasteRecordData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        val btnAddData: AppCompatButton =
            fragmentView.findViewById<AppCompatButton>(R.id.btnAddData)
        var quantity: TextInputEditText =  fragmentView.findViewById<TextInputEditText>(R.id.waste_quantity)

        btnAddData.setOnClickListener {

            if (HelperFunctions.noNullMinLengthOne(cookedProductSpinner.selectedItem?.toString()) && (HelperFunctions.isNumber(quantity.text.toString()) )) {
                viewLifecycleOwner.lifecycleScope.launch {
                    foodWasteRecordViewModel.addFoodWasteRecord(
                        FoodWasteRecord(
                            0, cookedProductSpinner.selectedItem.toString(), quantity.text.toString().toInt(),
                            Calendar.getInstance().time
                        )
                    )
                }
                ToastMaker.showToast("Success! Record added!", GetAppContext.appContext)
            } else {
                ToastMaker.showToast("Error: Enter valid data", GetAppContext.appContext)
            }
        }

        val btnBack: AppCompatButton = fragmentView.findViewById<AppCompatButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            fragmentView.findNavController()
                .navigate(R.id.action_foodWasteRecordFragment_to_inventoryManagementHomeFragment)
        }
        return fragmentView
    }


    override fun onDeleteFoodWasteRecordClickListener(foodWasteRecord: FoodWasteRecord) {

        var foodWasteRecordViewModel =
            ViewModelProvider(this).get(FoodWasteRecordViewModel::class.java)

        foodWasteRecordViewModel
            .deleteCleaningRecord(foodWasteRecord)
    }

}