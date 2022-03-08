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
import app.smartmanager.adapters.InventoryItemAdapter
import app.smartmanager.adapters.ProbeCalibrationRecordAdapter
import app.smartmanager.ui.setup.viewmodel.InventoryItemViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InventoryItemFragment : Fragment() {

    private lateinit var inventoryItemViewModel: InventoryItemViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.inventory_item_fragment, container, false)
        val floatingAddButton = fragmentView.findViewById<FloatingActionButton>(R.id.btnAdd)
        val backButton = fragmentView.findViewById<Button>(R.id.btnBack)

        //Implementing recyclerview
        val adapter = InventoryItemAdapter()
        val recyclerView = fragmentView.findViewById<RecyclerView>(R.id.displayDataView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Initialising the viewmodel
        inventoryItemViewModel = ViewModelProvider(this).get(InventoryItemViewModel::class.java)

        inventoryItemViewModel.readAllInventoryItemRecordData.observe(viewLifecycleOwner, Observer {
            inventoryItemData ->
            adapter.setDataSet(inventoryItemData)
        })

        //Attaching onClickListener to Add button (btnAdd)
        floatingAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_inventoryItemFragment_to_addInventoryItemFragment)
        }

        //Attaching onClickListener to BackButton (btnBack)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_inventoryItemFragment_to_initialSettings)
        }
        return fragmentView
    }

}