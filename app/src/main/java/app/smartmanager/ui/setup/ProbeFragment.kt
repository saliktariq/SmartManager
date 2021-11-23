package app.smartmanager.ui.setup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.ProbeAdapter

import app.smartmanager.databinding.ProbeFragmentBinding
import app.smartmanager.datalayer.entity.Probe
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.setup.viewmodel.ProbeViewModel
import app.smartmanager.ui.setup.viewmodel.ProbeViewModelFactory
import kotlinx.coroutines.launch


class ProbeFragment : Fragment(), ProbeAdapter.ProbeClickListenter {
    private var binding: ProbeFragmentBinding? = null
    private val viewBinding get() = binding!!

    private lateinit var probeList: RecyclerView
    lateinit var recyclerViewAdapter: ProbeAdapter





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProbeFragmentBinding.inflate(inflater, container, false)

        // Initialising the recycler view probeList

        probeList = binding!!.root.findViewById(R.id.showDatalayout)



        probeList.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = ProbeAdapter(this@ProbeFragment)
            adapter = recyclerViewAdapter

            /*
            Source for divider Item Decoration
            https://stackoverflow.com/questions/24618829/how-to-add-dividers-and-spaces-between-items-in-recyclerview
             */
            val dividerItemDecoration = DividerItemDecoration(
                probeList.getContext(),
                LinearLayoutManager(activity).getOrientation()
            )
            addItemDecoration(dividerItemDecoration)
        }

        // Variables to hold ProbeViewModel and ProbeViewModelFactory
        val viewModelFactory = ProbeViewModelFactory()
        val probeViewModel = ViewModelProvider(this, viewModelFactory).get(ProbeViewModel::class.java)

        probeViewModel.getAllProbesObserver().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setProbeData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        // Setting onClickListener to btnAddData
        viewBinding.btnAddData.setOnClickListener {

            if (viewBinding.probeName.text.toString().length < 2){
                Log.d("ProbeName Length ", viewBinding.probeName.text.toString().length.toString())
                Toast.makeText(GetAppContext.appContext,"Probe name should be greater than 1 character",Toast.LENGTH_SHORT).show()
            } else {
                //Setting viewModel variables as per entered data by user
                probeViewModel.probeName = viewBinding.probeName.text.toString()

                //Starting coroutine with application lifecycle scope
                viewLifecycleOwner.lifecycleScope.launch {

                    val result =  probeViewModel.insertProbeData(probeViewModel.probeName)
                    if(result){
                        ToastMaker.showToast("Probe successfully added",GetAppContext.appContext)
                    } else {
                        ToastMaker.showToast("Error adding probe. No duplicates allowed",GetAppContext.appContext)
                    }

                }
            }

        }

        viewBinding.btnBack.setOnClickListener {

            // Navigating to HomeScreen after processing data
            viewBinding.root.findNavController()
                .navigate(ProbeFragmentDirections.actionGlobalHomeScreen())
        }

        return binding!!.root
    }

    override fun onDeleteProbeClickListener(probe: Probe){
        // Variables to hold ProbeViewModel and ProbeViewModelFactory
        val viewModelFactory = ProbeViewModelFactory()
        val probeViewModel = ViewModelProvider(this, viewModelFactory).get(ProbeViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launch{
            probeViewModel.deleteProbeByProbeObject(probe)
        }

    }

//    override fun onProbeClickListener(probe: Probe) {
//
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }




}