package app.smartmanager.ui.reports

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.StaffTrainingRecordAdapter
import app.smartmanager.datalayer.entity.StaffTrainingRecord
import app.smartmanager.ui.foodsafetymanagement.stafftrainingrecord.viewmodel.StaffTrainingRecordViewModel
import app.smartmanager.ui.reports.viewmodel.StaffTrainingReportViewModel
import java.util.ArrayList

class StaffTrainingReportFragment : Fragment(), StaffTrainingRecordAdapter.StaffTrainingRecordClickListenter {



    private lateinit var staffTrainingRecordViewModel: StaffTrainingRecordViewModel
    lateinit var staffTrainingReportList: RecyclerView
    private lateinit var recyclerViewAdapter: StaffTrainingRecordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView =  inflater.inflate(R.layout.staff_training_report_fragment, container, false)
        val trainingReportButton: AppCompatButton = fragmentView.findViewById(R.id.training_report_button)
        val backToReportsHome: AppCompatButton = fragmentView.findViewById(R.id.btnbackToReportsHome)

        backToReportsHome.setOnClickListener {
            findNavController().navigate(R.id.action_staffTrainingReportFragment_to_reportsHomeFragment)
        }
        staffTrainingRecordViewModel = ViewModelProvider(this).get(StaffTrainingRecordViewModel::class.java)

        trainingReportButton.setOnClickListener {

            //Initialising the recycleView list
            staffTrainingReportList = fragmentView.findViewById(R.id.showReportView)

            //Setting layout for recycleView list
            staffTrainingReportList.apply {
                layoutManager = LinearLayoutManager(activity)
                recyclerViewAdapter = StaffTrainingRecordAdapter(this@StaffTrainingReportFragment)
                adapter = recyclerViewAdapter


                val dividerItemDecoration = DividerItemDecoration(
                    staffTrainingReportList.getContext(),
                    LinearLayoutManager(activity).getOrientation()
                )
                addItemDecoration(dividerItemDecoration)
            }

            staffTrainingRecordViewModel.getAllDataObserver().observe(viewLifecycleOwner, Observer {
                recyclerViewAdapter.setStaffTrainingRecordData(ArrayList(it))
                recyclerViewAdapter.notifyDataSetChanged()
            })


        }



        return fragmentView
    }

    override fun onDeleteStaffTrainingRecordClickListener(staffTrainingRecord: StaffTrainingRecord) {
        //Implementation not required
    }
}