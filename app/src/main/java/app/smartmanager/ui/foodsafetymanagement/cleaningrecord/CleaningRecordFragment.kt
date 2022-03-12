package app.smartmanager.ui.foodsafetymanagement.cleaningrecord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.CleaningRecordAdapter
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.foodsafetymanagement.cleaningrecord.viewmodel.CleaningRecordViewModel
import kotlinx.coroutines.launch
import java.util.*

class CleaningRecordFragment : Fragment(), CleaningRecordAdapter.CleaningRecordClickListenter {


    private lateinit var cleaningRecordList: RecyclerView
    lateinit var recyclerViewAdapter: CleaningRecordAdapter
    lateinit  var cleaningRecordViewModel: CleaningRecordViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.cleaning_record_fragment, container, false)

        //Initialising the recycleView list
        cleaningRecordList = fragmentView.findViewById(R.id.showDatalayout)


        //Setting layout for recycleView list
        cleaningRecordList.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = CleaningRecordAdapter(this@CleaningRecordFragment)
            adapter = recyclerViewAdapter


            val dividerItemDecoration = DividerItemDecoration(
                cleaningRecordList.getContext(),
                LinearLayoutManager(activity).getOrientation()
            )
            addItemDecoration(dividerItemDecoration)
        }

        //Defining spinner variables to access spinners on the UI
        val cleaningTaskNameSpinner: AppCompatSpinner =
            fragmentView.findViewById<AppCompatSpinner>(R.id.cleaningTaskName)

        //Arraylist to hold values retrieved from DB for spinner
        var cleaningTaskNameDataRetrieved = ArrayList<String>()

        //Setting ArrayAdapter for Spinner
        val chooseCleaningTaskNameAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item, cleaningTaskNameDataRetrieved
        )

        //Setting drop down view resource to adapter
        chooseCleaningTaskNameAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //attaching adapter to spinner
        cleaningTaskNameSpinner.setAdapter(chooseCleaningTaskNameAdapter)



        cleaningRecordViewModel = ViewModelProvider(this).get(CleaningRecordViewModel::class.java)

        cleaningRecordViewModel.listAllTasks.observe(viewLifecycleOwner){listOfTasks ->
            for (name in listOfTasks){
                cleaningTaskNameDataRetrieved.add(name.toString())
            }
            chooseCleaningTaskNameAdapter.notifyDataSetChanged()

        }

        cleaningRecordViewModel.getAllDataObserver().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setCleaningRecordData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        val btnAddData: AppCompatButton = fragmentView.findViewById<AppCompatButton>(R.id.btnAddData)


        btnAddData.setOnClickListener {
            if(HelperFunctions.noNullMinLengthOne(cleaningTaskNameSpinner.selectedItem.toString())){
                viewLifecycleOwner.lifecycleScope.launch{
                    cleaningRecordViewModel.addCleaningRecord(CleaningRecord(0,cleaningTaskNameSpinner.selectedItem.toString(),
                        Calendar.getInstance().time))
                }
                ToastMaker.showToast("Success! Record added!",GetAppContext.appContext)
            } else {
                ToastMaker.showToast("Error: Enter valid data", GetAppContext.appContext)
            }
        }

        val btnBack: AppCompatButton = fragmentView.findViewById<AppCompatButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            fragmentView.findNavController().navigate(R.id.action_cleaningRecordFragment_to_foodSafetyManagementHomeFragment)
        }




        return fragmentView
    }

    override fun onDeleteCleaningRecordClickListener(cleaningRecord: CleaningRecord) {
        var cleaningRecordViewModel = ViewModelProvider(this).get(CleaningRecordViewModel::class.java)

            cleaningRecordViewModel
                .deleteCleaningRecord(cleaningRecord)
        }



}