package app.smartmanager.ui.foodsafetymanagement.stafftrainingrecord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatSpinner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import app.smartmanager.adapters.StaffTrainingRecordAdapter
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.datalayer.entity.StaffTrainingRecord
import app.smartmanager.helper.FormattedDate
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.foodsafetymanagement.stafftrainingrecord.viewmodel.StaffTrainingRecordViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.*

class StaffTrainingRecordFragment : Fragment(), StaffTrainingRecordAdapter.StaffTrainingRecordClickListenter {

    private lateinit var staffTrainingRecordViewModel: StaffTrainingRecordViewModel
    private lateinit var staffTrainingRecordList: RecyclerView
    lateinit var recyclerViewAdapter: StaffTrainingRecordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.staff_training_record_fragment, container, false)
        staffTrainingRecordViewModel = ViewModelProvider(this).get(StaffTrainingRecordViewModel::class.java)

        val trainingTaskNameSpinner: AppCompatSpinner = fragmentView.findViewById(R.id.trainingTaskName)
        val staffName: TextInputEditText = fragmentView.findViewById(R.id.staffName)
        val btnAddData:AppCompatButton = fragmentView.findViewById(R.id.btnAddData)
        val datePickerObject: DatePicker = fragmentView.findViewById(R.id.trainingDate)
        val trainingDate: Date = Calendar.getInstance().time
val btnBack: AppCompatButton = fragmentView.findViewById(R.id.btnBack)

        val now = Calendar.getInstance()

        datePickerObject.init(now.get(Calendar.YEAR), now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)

        ) { view, year, month, day ->
            trainingDate.setMonth(month+1)
            trainingDate.setYear(year)
            trainingDate.setDate(day)
        }


        //Initialising the recycleView list
        staffTrainingRecordList = fragmentView.findViewById(R.id.showDatalayout)

        //Setting layout for recycleView list
        staffTrainingRecordList.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = StaffTrainingRecordAdapter(this@StaffTrainingRecordFragment)
            adapter = recyclerViewAdapter


            val dividerItemDecoration = DividerItemDecoration(
                staffTrainingRecordList.getContext(),
                LinearLayoutManager(activity).getOrientation()
            )
            addItemDecoration(dividerItemDecoration)
        }

        //Arraylist to hold values retrieved from DB for spinner
        var trainingTopicNameDataRetrieved = ArrayList<String>()

        //Setting ArrayAdapter for Spinner
        val chooseTrainingTopicNameAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item, trainingTopicNameDataRetrieved
        )

        //Setting drop down view resource to adapter
        chooseTrainingTopicNameAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //attaching adapter to spinner
        trainingTaskNameSpinner.setAdapter(chooseTrainingTopicNameAdapter)

        staffTrainingRecordViewModel.listAllTopics.observe(viewLifecycleOwner) { listOfTopics ->
            for (name in listOfTopics) {
                trainingTopicNameDataRetrieved.add(name.toString())
            }
            chooseTrainingTopicNameAdapter.notifyDataSetChanged()

            if (listOfTopics.isEmpty()){
                ToastMaker.showToast("Add Training Topic in SETUP before you can add training record", GetAppContext.appContext)
                findNavController().navigate(R.id.action_staffTrainingRecordFragment_to_staffTrainingTopicFragment)
            }
        }

        staffTrainingRecordViewModel.getAllDataObserver().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setStaffTrainingRecordData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        btnAddData.setOnClickListener {
            if (HelperFunctions.noNullMinLengthOne(staffName.text?.toString())) {
                viewLifecycleOwner.lifecycleScope.launch {
                    staffTrainingRecordViewModel.addStaffTrainingRecord(
                        StaffTrainingRecord(
                            0, trainingTaskNameSpinner.selectedItem.toString(),
                            staffName.text.toString(),
                            trainingDate
                        )
                    )
                }
                ToastMaker.showToast("Success! Record added!", GetAppContext.appContext)
            } else {
                ToastMaker.showToast("Error: Enter valid data", GetAppContext.appContext)
            }
        }

        btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_staffTrainingRecordFragment_to_foodSafetyManagementHomeFragment)
        }






        return fragmentView
    }


    override fun onDeleteStaffTrainingRecordClickListener(staffTrainingRecord: StaffTrainingRecord) {
        staffTrainingRecordViewModel = ViewModelProvider(this).get(StaffTrainingRecordViewModel::class.java)
staffTrainingRecordViewModel.deleteStaffTrainingRecord(staffTrainingRecord)
    }

}