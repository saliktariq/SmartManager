package app.smartmanager.ui.setup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import app.smartmanager.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SupplierFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.supplier_fragment, container, false)

        val floatingAddButton = fragmentView.findViewById<FloatingActionButton>(R.id.btnAdd)

        // Attaching onClickListener to Add button (floatingAddButton)
        floatingAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_supplierFragment_to_addSupplierFragment)

        }

        return fragmentView

    }

}

