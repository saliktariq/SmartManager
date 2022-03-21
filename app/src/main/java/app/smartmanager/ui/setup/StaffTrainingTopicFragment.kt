package app.smartmanager.ui.setup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.ProbeAdapter
import app.smartmanager.adapters.StaffTrainingTopicAdapter
import app.smartmanager.datalayer.entity.Probe
import app.smartmanager.datalayer.entity.StaffTrainingTopic
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.setup.viewmodel.ProbeViewModel
import app.smartmanager.ui.setup.viewmodel.ProbeViewModelFactory
import app.smartmanager.ui.setup.viewmodel.StaffTrainingTopicViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class StaffTrainingTopicFragment : Fragment(), StaffTrainingTopicAdapter.StaffTrainingTopicClickListener{

    private lateinit var staffTrainingTopicViewModel: StaffTrainingTopicViewModel
    lateinit var recyclerViewAdapter: StaffTrainingTopicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.staff_training_topic_fragment, container, false)

        staffTrainingTopicViewModel = ViewModelProvider(this).get(StaffTrainingTopicViewModel::class.java)
        val topicListRecyclerView: RecyclerView = fragmentView.findViewById(R.id.showDatalayout)
        topicListRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = StaffTrainingTopicAdapter(this@StaffTrainingTopicFragment)
            adapter = recyclerViewAdapter

            val dividerItemDecoration = DividerItemDecoration(
                topicListRecyclerView.getContext(),
                LinearLayoutManager(activity).getOrientation()
            )
            addItemDecoration(dividerItemDecoration)
        }
        staffTrainingTopicViewModel.getAllTopicObserver().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setTrainingTopicsData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        val addDataButton: Button = fragmentView.findViewById<Button>(R.id.btnAddData)
        val topicName: TextInputEditText = fragmentView.findViewById(R.id.topicName)

        addDataButton.setOnClickListener{
            if (topicName.text.toString().length < 2){
                Toast.makeText(
                    GetAppContext.appContext,"Topic name should be greater than 1 character",
                    Toast.LENGTH_SHORT).show()
            } else {
                //Setting viewModel variables as per entered data by user
                staffTrainingTopicViewModel.topicName = topicName.text.toString()

                //Starting coroutine with application lifecycle scope
                viewLifecycleOwner.lifecycleScope.launch {

                    val result =  staffTrainingTopicViewModel.insertTopicData(staffTrainingTopicViewModel.topicName)
                    if(result){
                        ToastMaker.showToast("Topic successfully added", GetAppContext.appContext)
                    } else {
                        ToastMaker.showToast("Error adding topic. No nulls and duplicates allowed",
                            GetAppContext.appContext)
                    }

                }
            }
        }

fragmentView.findViewById<Button>(R.id.btnBack).setOnClickListener {
    findNavController().navigate(R.id.action_global_initialSettings)
}



        return fragmentView

    }

    override fun onDeleteTopicClickListener(staffTrainingTopic: StaffTrainingTopic){
        staffTrainingTopicViewModel = ViewModelProvider(this).get(StaffTrainingTopicViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launch{
            staffTrainingTopicViewModel.deleteStaffTrainingTopic(staffTrainingTopic)
        }

    }
}