package app.smartmanager.ui.setup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Layout
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
import app.smartmanager.adapters.CookedProductItemAdapter
import app.smartmanager.adapters.ProbeCalibrationRecordAdapter
import app.smartmanager.ui.setup.viewmodel.CookedProductItemViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CookedProductItemFragment : Fragment() {

    private lateinit var cookedProductItemViewModel: CookedProductItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.cooked_product_item_fragment, container, false)
        val floatingAddButton = fragmentView.findViewById<FloatingActionButton>(R.id.btnAdd)
        val backButton = fragmentView.findViewById<Button>(R.id.btnBack)

        //Implementing recyclerview
        val adapter = CookedProductItemAdapter()
        val recyclerView = fragmentView.findViewById<RecyclerView>(R.id.displayDataView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Initialising the view model

        cookedProductItemViewModel = ViewModelProvider(this).get(CookedProductItemViewModel::class.java)

        cookedProductItemViewModel.readAllCookedProductItemRecordData.observe(viewLifecycleOwner, Observer {
            cookedProductItemData ->
            adapter.setDataSet(cookedProductItemData)
        })

        //Attaching onClickListener to Add Button (floating add button)
        floatingAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_cookedProductItemFragment_to_addCookedProductItemFragment)
        }

        //Attaching onClickListener to Back button
        backButton.setOnClickListener{
            findNavController().navigate(R.id.action_cookedProductItemFragment_to_initialSettings)
        }

        return fragmentView



    }


}