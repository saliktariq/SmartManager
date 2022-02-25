package app.smartmanager.ui.setup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.SupplierAdapter
import app.smartmanager.ui.setup.viewmodel.ControlChecksViewModel
import app.smartmanager.ui.setup.viewmodel.SupplierViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ControlChecksFragment : Fragment() {
    private lateinit var controlChecksViewModel: ControlChecksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.control_checks_fragment, container, false)

        val floatingAddButton = fragmentView.findViewById<FloatingActionButton>(R.id.btnAdd)
        val backButton = fragmentView.findViewById<Button>(R.id.btnBack)



        //Implementing recyclerview
        val adapter = ControlChecksAdapter()
        val recyclerView = fragmentView.findViewById<RecyclerView>(R.id.displayDataView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        // Initialising the controlChecksViewModel
        controlChecksViewModel = ViewModelProvider(this).get(ControlChecksViewModel::class.java)


        controlChecksViewModel.readAllControlChecksData.observe(viewLifecycleOwner, Observer { controlChecks ->
            adapter.setDataSet(controlChecks)

        })
        // Attaching onClickListener to Add button (floatingAddButton)
        floatingAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_controlChecksFragment_to_addControlChecksFragment)

        }

        // Attaching onClickListener to Back button (backButton)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_controlChecksFragment_to_initialSettings)
        }
        return fragmentView

    }
}
