package app.smartmanager.ui.inventorymanagement.dailyinventory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.DailyInventoryRecordAdapter
import app.smartmanager.adapters.FoodWasteRecordAdapter
import app.smartmanager.datalayer.entity.DailyInventoryRecord
import app.smartmanager.datalayer.entity.FoodWasteRecord
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.inventorymanagement.dailyinventory.viewmodel.DailyInventoryViewModel
import app.smartmanager.ui.inventorymanagement.foodwastagerecord.viewmodel.FoodWasteRecordViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.*

class DailyInventoryFragment: Fragment(), DailyInventoryRecordAdapter.DailyInventoryRecordClickListenter{


    private lateinit var dailyInventoryRecordList: RecyclerView
    lateinit var recyclerViewAdapter: DailyInventoryRecordAdapter
    private lateinit var dailyInventoryViewModel: DailyInventoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView =  inflater.inflate(R.layout.daily_inventory_fragment, container, false)

        //Initialising the recycleView list
        dailyInventoryRecordList = fragmentView.findViewById(R.id.showDatalayout)

        //Setting layout for recycleView list
        dailyInventoryRecordList.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = DailyInventoryRecordAdapter(this@DailyInventoryFragment)
            adapter = recyclerViewAdapter

            val dividerItemDecoration = DividerItemDecoration(
                dailyInventoryRecordList.getContext(),
                LinearLayoutManager(activity).getOrientation()
            )
            addItemDecoration(dividerItemDecoration)
        }

        //Defining spinner variables to access spinners on the UI
        val productNameSpinner: AppCompatSpinner =
            fragmentView.findViewById<AppCompatSpinner>(R.id.productName)

        //Arraylist to hold values retrieved from DB for spinner
        var productNameDataRetrieved = ArrayList<String>()

        //Setting ArrayAdapter for Spinner
        val productNameAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item, productNameDataRetrieved
        )

        //Setting drop down view resource to adapter
        productNameAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //attaching adapter to spinner
        productNameSpinner.setAdapter(productNameAdapter)

        dailyInventoryViewModel  = ViewModelProvider(this).get(DailyInventoryViewModel::class.java)

        dailyInventoryViewModel.listAllInventoryItems.observe(viewLifecycleOwner) { listOfItems ->
            for (name in listOfItems) {
                productNameDataRetrieved.add(name.toString())
            }
            productNameAdapter.notifyDataSetChanged()

            if (listOfItems.isEmpty()){
                ToastMaker.showToast("Add Products in SETUP before you can add Inventory record", GetAppContext.appContext)
                findNavController().navigate(R.id.action_dailyInventoryFragment_to_inventoryItemFragment)
            }
        }

        dailyInventoryViewModel.getAllDataObserver().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setDailyInventoryRecordData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        val btnAddData: AppCompatButton =
            fragmentView.findViewById<AppCompatButton>(R.id.btnAddData)

        var quantity: TextInputEditText =  fragmentView.findViewById<TextInputEditText>(R.id.inventory_quantity)

        btnAddData.setOnClickListener {
            if (HelperFunctions.noNullMinLengthOne(productNameSpinner.selectedItem?.toString()) && (HelperFunctions.isNumber(quantity.text.toString()) )) {
                viewLifecycleOwner.lifecycleScope.launch {
                    dailyInventoryViewModel.addDailyInventoryRecord(
                        DailyInventoryRecord(
                            0, productNameSpinner.selectedItem.toString(), quantity.text.toString().toInt(),
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
                .navigate(R.id.action_dailyInventoryFragment_to_inventoryManagementHomeFragment)
        }
        return fragmentView
    }

    override fun onDeleteDailyInventoryRecordClickListener(dailyInventoryRecord: DailyInventoryRecord) {
        var dailyInventoryViewModel =
            ViewModelProvider(this).get(DailyInventoryViewModel::class.java)

        dailyInventoryViewModel
            .deleteDailyInventoryRecord(dailyInventoryRecord)
    }


}