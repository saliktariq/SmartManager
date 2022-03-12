package app.smartmanager.ui.foodsafetymanagement.equipmenttemperature

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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.EquipmentTemperatureRecordAdapter
import app.smartmanager.adapters.FoodWasteRecordAdapter
import app.smartmanager.datalayer.entity.EquipmentTemperatureRecord
import app.smartmanager.datalayer.entity.FoodWasteRecord
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.foodsafetymanagement.equipmenttemperature.viewmodel.EquipmentTemperatureViewModel
import app.smartmanager.ui.inventorymanagement.foodwastagerecord.viewmodel.FoodWasteRecordViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.*

class EquipmentTemperatureFragment : Fragment(), EquipmentTemperatureRecordAdapter.EquipmentTemperatureRecordClickListener {

    private lateinit var equipmentTemperatureRecordList: RecyclerView
    lateinit var recyclerViewAdapter: EquipmentTemperatureRecordAdapter
    private lateinit var equipmentTemperatureViewModel: EquipmentTemperatureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.equipment_temperature_fragment, container, false)

        //Initialising the recycleView list
        equipmentTemperatureRecordList = fragmentView.findViewById(R.id.showDatalayout)

        //Setting layout for recycleView list
        equipmentTemperatureRecordList.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = EquipmentTemperatureRecordAdapter(this@EquipmentTemperatureFragment)
            adapter = recyclerViewAdapter

            val dividerItemDecoration = DividerItemDecoration(
                equipmentTemperatureRecordList.getContext(),
                LinearLayoutManager(activity).getOrientation()
            )
            addItemDecoration(dividerItemDecoration)
        }

        //Defining spinner variables to access spinners on the UI
        val equipmentNameSpinner: AppCompatSpinner =
            fragmentView.findViewById<AppCompatSpinner>(R.id.select_equipment)

        //Arraylist to hold values retrieved from DB for spinner
        var equipmentNameDataRetrieved = ArrayList<String>()

        //Setting ArrayAdapter for Spinner
        val equipmentNameAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item, equipmentNameDataRetrieved
        )

        //Setting drop down view resource to adapter
        equipmentNameAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //attaching adapter to spinner
        equipmentNameSpinner.setAdapter(equipmentNameAdapter)

        equipmentTemperatureViewModel = ViewModelProvider(this).get(EquipmentTemperatureViewModel::class.java)

        equipmentTemperatureViewModel.listAllEquipment.observe(viewLifecycleOwner) { listOfEquipment ->
            for (name in listOfEquipment) {
                equipmentNameDataRetrieved.add(name.toString())
            }
            equipmentNameAdapter.notifyDataSetChanged()
        }

        equipmentTemperatureViewModel.getAllDataObserver().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setEquipmentTemperatureRecordData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        val btnAddData: AppCompatButton =
            fragmentView.findViewById<AppCompatButton>(R.id.btnAddData)


        val temperature: TextInputEditText = fragmentView.findViewById(R.id.equipment_temperature)

        btnAddData.setOnClickListener {

            if (HelperFunctions.noNullMinLengthOne(equipmentNameSpinner.selectedItem?.toString()) && (HelperFunctions.isNumber(temperature.text.toString()) )) {
                viewLifecycleOwner.lifecycleScope.launch {
                    equipmentTemperatureViewModel.addEquipmentTemperatureRecord(
                        EquipmentTemperatureRecord(
                            0, equipmentNameSpinner.selectedItem.toString(), temperature.text.toString().toFloat(),
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
                .navigate(R.id.action_equipmentTemperatureFragment_to_foodSafetyManagementHomeFragment)
        }
        return fragmentView
    }

    override fun onDeleteEquipmentTemperatureRecordClickListener(equipmentTemperatureRecord: EquipmentTemperatureRecord) {
        var equipmentTemperatureViewModel =
            ViewModelProvider(this).get(EquipmentTemperatureViewModel::class.java)

        equipmentTemperatureViewModel
            .deleteCleaningRecord(equipmentTemperatureRecord)
    }


}