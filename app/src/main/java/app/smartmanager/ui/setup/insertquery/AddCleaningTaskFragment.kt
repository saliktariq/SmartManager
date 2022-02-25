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
import app.smartmanager.ui.setup.viewmodel.CleaningTaskViewModel
import app.smartmanager.ui.setup.viewmodel.EquipmentViewModel

class AddCleaningTaskFragment : Fragment() {

    private lateinit var cleaningTaskViewModel: CleaningTaskViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_add_cleaning_task, container, false)

        // Initialising the cleaningTaskViewModel
        cleaningTaskViewModel = ViewModelProvider(this).get(CleaningTaskViewModel::class.java)


        val addButton: Button = fragmentView.findViewById(R.id.btnAdd)



        // Variables to hold fragment data
        lateinit var cleaningTaskName: String //This property can not be null as per our entity definition
        var cleaningTaskDescription: String? = null
        var taskFrequency: String? = null


// Attaching onClickListener to Add button (addButton)
        addButton.setOnClickListener {

            //Initialising fragment data variables with user entered data
            cleaningTaskName = fragmentView.findViewById<EditText>(R.id.cleaningTaskName).text.toString()
            cleaningTaskDescription = fragmentView.findViewById<EditText>(R.id.cleaningTaskDescription).text.toString()
            taskFrequency = fragmentView.findViewById<EditText>(R.id.taskFrequency).text.toString()


            val insertData = cleaningTaskViewModel.insertData(cleaningTaskName, cleaningTaskDescription,taskFrequency )

            if(insertData){
                //If insertData operation is successful navigate back to Equipment Fragment
                findNavController().navigate(R.id.action_addCleaningTaskFragment_to_cleaningTaskFragment)
            }

        }

        return fragmentView

    }



}