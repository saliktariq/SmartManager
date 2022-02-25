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
import app.smartmanager.ui.setup.viewmodel.ControlChecksViewModel
import app.smartmanager.ui.setup.viewmodel.EquipmentViewModel

class AddControlChecksFragment : Fragment() {
    private lateinit var controlChecksViewModel: ControlChecksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_add_control_checks, container, false)

        // Initialising the controlChecksViewModel
        controlChecksViewModel = ViewModelProvider(this).get(ControlChecksViewModel::class.java)


        val addButton: Button = fragmentView.findViewById(R.id.btnAdd)



        // Variables to hold fragment data
        lateinit var controlName: String //This property can not be null as per our entity definition
        var controlDescription: String? = null



// Attaching onClickListener to Add button (addButton)
        addButton.setOnClickListener {

            //Initialising fragment data variables with user entered data
            controlName = fragmentView.findViewById<EditText>(R.id.controlName).text.toString()
            controlDescription = fragmentView.findViewById<EditText>(R.id.controlDescription).text.toString()

            val insertData = controlChecksViewModel.insertData(controlName,controlDescription )

            if(insertData){
                //If insertData operation is successful navigate back to Equipment Fragment
                findNavController().navigate(R.id.action_addControlChecksFragment_to_controlChecksFragment)
            }

        }

        return fragmentView

    }



}