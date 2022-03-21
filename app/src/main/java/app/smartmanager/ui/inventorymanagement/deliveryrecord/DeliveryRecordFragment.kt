package app.smartmanager.ui.inventorymanagement.deliveryrecord

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
import app.smartmanager.adapters.DeliveryRecordAdapter
import app.smartmanager.adapters.FoodWasteRecordAdapter
import app.smartmanager.datalayer.entity.DeliveryRecord
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.inventorymanagement.deliveryrecord.viewmodel.DeliveryRecordViewModel
import app.smartmanager.ui.inventorymanagement.foodwastagerecord.viewmodel.FoodWasteRecordViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.*

class DeliveryRecordFragment : Fragment(), DeliveryRecordAdapter.DeliveryRecordClickListenter {

    private lateinit var deliveryRecordList: RecyclerView
    lateinit var recyclerViewAdapter: DeliveryRecordAdapter
    private lateinit var deliveryRecordViewModel: DeliveryRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.delivery_record_fragment, container, false)

        //Initialising the recycleView list
        deliveryRecordList = fragmentView.findViewById(R.id.showDatalayout)

        //Setting layout for recycleView list
        deliveryRecordList.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = DeliveryRecordAdapter(this@DeliveryRecordFragment)
            adapter = recyclerViewAdapter

            val dividerItemDecoration = DividerItemDecoration(
                deliveryRecordList.getContext(),
                LinearLayoutManager(activity).getOrientation()
            )
            addItemDecoration(dividerItemDecoration)
        }


        //Defining spinner variables to access spinners on the UI
        val productNameSpinner: AppCompatSpinner =
            fragmentView.findViewById<AppCompatSpinner>(R.id.productName)
        val supplierNameSpinner: AppCompatSpinner =
            fragmentView.findViewById<AppCompatSpinner>(R.id.SupplierName)

        //Arraylist to hold values retrieved from DB for spinner
        var productNameDataRetrieved = ArrayList<String>()
        var supplierNameDataRetrieved = ArrayList<String>()

        //Setting ArrayAdapter for Spinners
        val productNameAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item, productNameDataRetrieved
        )
        val supplierNameAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item, supplierNameDataRetrieved
        )

        //Setting drop down view resource to adapters
        productNameAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        supplierNameAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //attaching adapters to spinners
        productNameSpinner.setAdapter(productNameAdapter)
        supplierNameSpinner.setAdapter(supplierNameAdapter)

        deliveryRecordViewModel = ViewModelProvider(this).get(DeliveryRecordViewModel::class.java)

        deliveryRecordViewModel.listAllProducts.observe(viewLifecycleOwner) { listOfItems ->
            for (name in listOfItems) {
                productNameDataRetrieved.add(name.toString())
            }
            productNameAdapter.notifyDataSetChanged()
            if (listOfItems.isEmpty()){
                ToastMaker.showToast("Add Inventory items in SETUP before adding delivery record", GetAppContext.appContext)
                findNavController().navigate(R.id.action_global_initialSettings)
            }
        }
        deliveryRecordViewModel.listAllSuppliers.observe(viewLifecycleOwner) { listOfItems ->
            for (name in listOfItems) {
                supplierNameDataRetrieved.add(name.toString())
            }
            supplierNameAdapter.notifyDataSetChanged()
            if (listOfItems.isEmpty()){
                ToastMaker.showToast("Add Supplier in SETUP before adding delivery record", GetAppContext.appContext)
                findNavController().navigate(R.id.action_global_initialSettings)
            }
        }

        deliveryRecordViewModel.getAllDataObserver().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setDeliveryRecordData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        val btnAddData: AppCompatButton =
            fragmentView.findViewById<AppCompatButton>(R.id.btnAddData)

        val quantity: TextInputEditText = fragmentView.findViewById(R.id.quantity)
        val temperature: TextInputEditText = fragmentView.findViewById(R.id.temperature)

        btnAddData.setOnClickListener {
            if (HelperFunctions.noNullMinLengthOne(productNameSpinner.selectedItem?.toString()) && HelperFunctions.noNullMinLengthOne(
                    supplierNameSpinner.selectedItem?.toString()
                ) && HelperFunctions.isNumber(quantity.text.toString()) && HelperFunctions.isNumber(
                    temperature.text.toString()
                )
            ) {
                viewLifecycleOwner.lifecycleScope.launch {
                    deliveryRecordViewModel.addDeliveryRecord(
                        DeliveryRecord(
                            0,
                            productNameSpinner.selectedItem.toString(),
                            supplierNameSpinner.selectedItem.toString(),
                            quantity.text.toString().toInt(),
                            temperature.text.toString().toFloat(),
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
                .navigate(R.id.action_deliveryRecordFragment_to_inventoryManagementHomeFragment)
        }

        return fragmentView
    }


    override fun onDeleteDeliveryRecordClickListener(deliveryRecord: DeliveryRecord) {
        var deliveryRecordViewModel =
            ViewModelProvider(this).get(DeliveryRecordViewModel::class.java)

        deliveryRecordViewModel
            .deleteDeliveryRecord(deliveryRecord)
    }

}