package app.smartmanager.ui.setup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.SupplierAdapter
import app.smartmanager.ui.setup.viewmodel.SupplierViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SupplierFragment : Fragment() {

    private lateinit var supplierViewModel: SupplierViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.supplier_fragment, container, false)

        val floatingAddButton = fragmentView.findViewById<FloatingActionButton>(R.id.btnAdd)
        val backButton = fragmentView.findViewById<Button>(R.id.btnBack)



        //Implementing recyclerview
        val adapter = SupplierAdapter()
        val recyclerView = fragmentView.findViewById<RecyclerView>(R.id.displayDataView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        // Initialising the supplierViewModel
        supplierViewModel = ViewModelProvider(this).get(SupplierViewModel::class.java)


        supplierViewModel.readAllSupplierData.observe(viewLifecycleOwner, Observer { supplier ->
            adapter.setDataSet(supplier)

        })
        // Attaching onClickListener to Add button (floatingAddButton)
        floatingAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_supplierFragment_to_addSupplierFragment)

        }

        // Attaching onClickListener to Back button (backButton)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_supplierFragment_to_initialSettings)
        }
        return fragmentView

    }

}

