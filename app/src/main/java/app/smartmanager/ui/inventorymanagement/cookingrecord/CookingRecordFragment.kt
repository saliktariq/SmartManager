package app.smartmanager.ui.inventorymanagement.cookingrecord

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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.CookingRecordAdapter
import app.smartmanager.adapters.FoodWasteRecordAdapter
import app.smartmanager.datalayer.entity.CookingRecord
import app.smartmanager.datalayer.entity.FoodWasteRecord
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.inventorymanagement.cookingrecord.viewmodel.CookingRecordViewModel
import app.smartmanager.ui.inventorymanagement.foodwastagerecord.viewmodel.FoodWasteRecordViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.*

class CookingRecordFragment : Fragment(), CookingRecordAdapter.CookingRecordClickListenter {

    private lateinit var cookingRecordList: RecyclerView
    lateinit var recyclerViewAdapter: CookingRecordAdapter
    private lateinit var cookingRecordViewModel: CookingRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView =  inflater.inflate(R.layout.cooking_record_fragment, container, false)

        //Initialising the recycleView list
        cookingRecordList = fragmentView.findViewById(R.id.showDatalayout)

        //Setting layout for recycleView list
        cookingRecordList.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = CookingRecordAdapter(this@CookingRecordFragment)
            adapter = recyclerViewAdapter

            val dividerItemDecoration = DividerItemDecoration(
                cookingRecordList.getContext(),
                LinearLayoutManager(activity).getOrientation()
            )
            addItemDecoration(dividerItemDecoration)
        }
        //Defining spinner variables to access spinners on the UI
        val cookedProductSpinner: AppCompatSpinner =
            fragmentView.findViewById<AppCompatSpinner>(R.id.cookedProductSpinner)

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

        cookingRecordViewModel = ViewModelProvider(this).get(CookingRecordViewModel::class.java)

        cookingRecordViewModel.listAllCookedProductItem.observe(viewLifecycleOwner) { listOfItems ->
            for (name in listOfItems) {
                cookedProductNameDataRetrieved.add(name.toString())
            }
            cookedProductAdapter.notifyDataSetChanged()
        }

        cookingRecordViewModel.getAllDataObserver().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setCookingRecordData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })
        val btnAddData: AppCompatButton =
            fragmentView.findViewById<AppCompatButton>(R.id.btnAddData)

        var quantity: TextInputEditText =  fragmentView.findViewById<TextInputEditText>(R.id.cooked_quantity)

        btnAddData.setOnClickListener {

            if (HelperFunctions.noNullMinLengthOne(cookedProductSpinner.selectedItem.toString()) && (HelperFunctions.isNumber(quantity.text.toString()) )) {
                viewLifecycleOwner.lifecycleScope.launch {
                    cookingRecordViewModel.addCookingRecord(
                        CookingRecord(
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
                .navigate(R.id.action_cookingRecordFragment_to_inventoryManagementHomeFragment)
        }
        return fragmentView
    }



    override fun onDeleteCookingRecordClickListener(cookingRecord: CookingRecord) {
        var cookingRecordViewModel =
            ViewModelProvider(this).get(CookingRecordViewModel::class.java)

        cookingRecordViewModel
            .deleteCookingRecord(cookingRecord)
    }

}