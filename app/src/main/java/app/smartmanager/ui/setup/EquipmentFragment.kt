package app.smartmanager.ui.setup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.EquipmentAdapter
import app.smartmanager.adapters.SupplierAdapter
import app.smartmanager.ui.setup.viewmodel.EquipmentViewModel
import app.smartmanager.ui.setup.viewmodel.SupplierViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EquipmentFragment : Fragment() {

    private lateinit var equipmentViewModel: EquipmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.equipment_fragment, container, false)

        val floatingAddButton = fragmentView.findViewById<FloatingActionButton>(R.id.btnAdd)
        val backButton = fragmentView.findViewById<Button>(R.id.btnBack)



        //Implementing recyclerview
        val adapter = EquipmentAdapter()
        val recyclerView = fragmentView.findViewById<RecyclerView>(R.id.displayDataView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        // Initialising the equipmentViewModel
        equipmentViewModel = ViewModelProvider(this).get(EquipmentViewModel::class.java)


        equipmentViewModel.readAllEquipmentData.observe(viewLifecycleOwner, Observer { equipment ->
            adapter.setDataSet(equipment)

        })
        // Attaching onClickListener to Add button (floatingAddButton)
        floatingAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_equipmentFragment_to_addEquipmentFragment)

        }

        // Attaching onClickListener to Back button (backButton)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_equipmentFragment_to_initialSettings)
        }



        return fragmentView

    }

}