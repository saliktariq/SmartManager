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
import app.smartmanager.ui.setup.viewmodel.ChemicalListCOSHHViewModel
import app.smartmanager.ui.setup.viewmodel.EquipmentViewModel


class AddChemicalListCOSHHFragment : Fragment() {

    private lateinit var chemicalListCOSHHViewModel: ChemicalListCOSHHViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_add_chemical_list_coshh, container, false)

        // Initialising the equipmentViewModel
        chemicalListCOSHHViewModel = ViewModelProvider(this).get(ChemicalListCOSHHViewModel::class.java)


        val addButton: Button = fragmentView.findViewById(R.id.btnAdd)



        // Variables to hold fragment data
        lateinit var chemicalName: String //This property can not be null as per our entity definition
        var chemicalPurpose: String? = null
        var chemicalConcentration: String? = null
        var notes: String? = null

// Attaching onClickListener to Add button (addButton)
        addButton.setOnClickListener {

            //Initialising fragment data variables with user entered data
            chemicalName = fragmentView.findViewById<EditText>(R.id.chemicalName).text.toString()
            chemicalPurpose = fragmentView.findViewById<EditText>(R.id.chemicalPurpose).text.toString()
            chemicalConcentration = fragmentView.findViewById<EditText>(R.id.chemicalConcentration).text.toString()
            notes = fragmentView.findViewById<EditText>(R.id.notes).text.toString()

            val insertData = chemicalListCOSHHViewModel.insertData(chemicalName, chemicalPurpose, chemicalConcentration, notes)

            if(insertData){
                //If insertData operation is successful navigate back to Equipment Fragment
                findNavController().navigate(R.id.action_addChemicalListCOSHHFragment_to_chemicalListCOSHHFragment)
            }

        }

        return fragmentView

    }



}