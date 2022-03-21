package app.smartmanager.ui.foodsafetymanagement.producttemperature

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
import app.smartmanager.adapters.CookedProductTemperatureRecordAdapter
import app.smartmanager.adapters.FoodWasteRecordAdapter
import app.smartmanager.datalayer.entity.CookedProductTemperatureRecord
import app.smartmanager.datalayer.entity.FoodWasteRecord
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.foodsafetymanagement.producttemperature.viewmodel.CookedProductTemperatureViewModel
import app.smartmanager.ui.inventorymanagement.foodwastagerecord.viewmodel.FoodWasteRecordViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.*

class CookedProductTemperatureFragment : Fragment(), CookedProductTemperatureRecordAdapter.CookedProductTemperatureRecordClickListener {

    private lateinit var cookedProductTemperatureRecordList: RecyclerView
    lateinit var recyclerViewAdapter: CookedProductTemperatureRecordAdapter
    private lateinit var cookedProductTemperatureViewModel: CookedProductTemperatureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.cooked_product_temperature_fragment, container, false)

        //Initialising the recycleView list
        cookedProductTemperatureRecordList = fragmentView.findViewById(R.id.showDatalayout)

        //Setting layout for recycleView list
        cookedProductTemperatureRecordList.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = CookedProductTemperatureRecordAdapter(this@CookedProductTemperatureFragment)
            adapter = recyclerViewAdapter

            val dividerItemDecoration = DividerItemDecoration(
                cookedProductTemperatureRecordList.getContext(),
                LinearLayoutManager(activity).getOrientation()
            )
            addItemDecoration(dividerItemDecoration)
        }

        //Defining spinner variables to access spinners on the UI
        val cookedProductSpinner: AppCompatSpinner =
            fragmentView.findViewById<AppCompatSpinner>(R.id.cookedProductItem)

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

        cookedProductTemperatureViewModel = ViewModelProvider(this).get(CookedProductTemperatureViewModel::class.java)
        cookedProductTemperatureViewModel.listAllCookedProduct.observe(viewLifecycleOwner) { listOfCookedItems ->
            for (name in listOfCookedItems) {
                cookedProductNameDataRetrieved.add(name.toString())
            }
            cookedProductAdapter.notifyDataSetChanged()
            if (listOfCookedItems.isEmpty()){
                ToastMaker.showToast("Add Cooked Products in SETUP before you can add cooked product temperature record", GetAppContext.appContext)
                findNavController().navigate(R.id.action_cookedProductTemperatureFragment_to_cookedProductItemFragment)
            }
        }

        cookedProductTemperatureViewModel.getAllDataObserver().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setCookedProductTemperatureData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        val btnAddData: AppCompatButton =
            fragmentView.findViewById<AppCompatButton>(R.id.btnAddData)

        var cookedTemperature: TextInputEditText = fragmentView.findViewById(R.id.cooked_temperature)

        btnAddData.setOnClickListener {

            if (HelperFunctions.noNullMinLengthOne(cookedProductSpinner.selectedItem?.toString()) && (HelperFunctions.isNumber(cookedTemperature.text.toString()) )) {
                viewLifecycleOwner.lifecycleScope.launch {
                    cookedProductTemperatureViewModel.addCookedProductTemperatureRecord(
                        CookedProductTemperatureRecord(
                            0, cookedProductSpinner.selectedItem.toString(), cookedTemperature.text.toString().toFloat(),
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
                .navigate(R.id.action_cookedProductTemperatureFragment_to_foodSafetyManagementHomeFragment)
        }


        return fragmentView
    }

    override fun onDeleteCookedProductTemperatureRecordClickListener(cookedProductTemperatureRecord: CookedProductTemperatureRecord) {
        var cookedProductTemperatureViewModel =
            ViewModelProvider(this).get(CookedProductTemperatureViewModel::class.java)

        cookedProductTemperatureViewModel
            .deleteCookedProductTemperatureRecord(cookedProductTemperatureRecord)
    }
}