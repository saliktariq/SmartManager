package app.smartmanager.ui.setup.insertquery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.smartmanager.R
import app.smartmanager.ui.setup.viewmodel.EquipmentViewModel
import app.smartmanager.ui.setup.viewmodel.SupplierViewModel


class AddEquipmentFragment : Fragment() {

    private lateinit var equipmentViewModel: EquipmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_add_equipment, container, false)

        // Initialising the equipmentViewModel
        equipmentViewModel = ViewModelProvider(this).get(EquipmentViewModel::class.java)


        val addButton: Button = fragmentView.findViewById(R.id.btnAdd)



        // Variables to hold fragment data
        lateinit var equipmentName: String //This property can not be null as per our entity definition


// Attaching onClickListener to Add button (addButton)
        addButton.setOnClickListener {

            //Initialising fragment data variables with user entered data
            equipmentName = fragmentView.findViewById<EditText>(R.id.equipmentName).text.toString()

            val insertData = equipmentViewModel.insertData(equipmentName)

            if(insertData){
                //If insertData operation is successful navigate back to Equipment Fragment
                findNavController().navigate(R.id.action_addEquipmentFragment_to_equipmentFragment)
            }

        }

        return fragmentView

    }



}