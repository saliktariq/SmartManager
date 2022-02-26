package app.smartmanager.ui.setup.updatequery

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.smartmanager.R
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.setup.viewmodel.CleaningTaskViewModel
import app.smartmanager.ui.setup.viewmodel.EquipmentViewModel
import app.smartmanager.ui.setup.viewmodel.SupplierViewModel
import kotlin.properties.Delegates


class UpdateCleaningTaskFragment : Fragment() {

    private lateinit var cleaningTaskViewModel: CleaningTaskViewModel
    private val args by navArgs<UpdateCleaningTaskFragmentArgs>()


    // Variables to hold fragment data
    var cleaningTaskID by Delegates.notNull<Long>()
    lateinit var cleaningTaskName: String //This property can not be null as per our entity definition
    var cleaningTaskDescription: String? = null
    var taskFrequency: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =  inflater.inflate(R.layout.fragment_update_cleaning_task, container, false)

        // Initialising the cleaningTaskViewModel
        cleaningTaskViewModel = ViewModelProvider(this).get(CleaningTaskViewModel::class.java)

        //Populating the Fragment fields with received arguments
        fragmentView.findViewById<EditText>(R.id.cleaningTaskName).setText(args.currentRecord.name).toString()
        fragmentView.findViewById<EditText>(R.id.cleaningTaskDescription).setText(args.currentRecord.description).toString()
        fragmentView.findViewById<EditText>(R.id.taskFrequency).setText(args.currentRecord.frequency).toString()


        val updateButton: Button = fragmentView.findViewById(R.id.btnUpdate)

        //Setting onClickListener for update button
        updateButton.setOnClickListener {

            //Initialising fragment data variables with updated  entered data
            cleaningTaskID = args.currentRecord.id.toLong()
            cleaningTaskName = fragmentView.findViewById<EditText>(R.id.cleaningTaskName).text.toString()
            cleaningTaskDescription = fragmentView.findViewById<EditText>(R.id.cleaningTaskDescription).text.toString()
            taskFrequency = fragmentView.findViewById<EditText>(R.id.taskFrequency).text.toString()


            val updateData = cleaningTaskViewModel.updateData(cleaningTaskID, cleaningTaskName,cleaningTaskDescription, taskFrequency )

            if(updateData){
                //If updateData operation is successful navigate back to CleaningTask Fragment
                findNavController().navigate(R.id.action_updateCleaningTaskFragment_to_cleaningTaskFragment)
            }
            // Adding menu to Update Supplier View
            setHasOptionsMenu(true)
        }
        return fragmentView
    }
    //Inflating the menu layout in supplier fragment
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //   super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_option, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.optionDelete){
            val deleteAlert = AlertDialog.Builder(requireContext())
            deleteAlert.setPositiveButton("Yes"){_, _ ->

                cleaningTaskViewModel.deleteCleaningTask(args.currentRecord)
                ToastMaker.showToast("${args.currentRecord.name} deleted!", GetAppContext.appContext)

                findNavController().navigate(R.id.action_updateCleaningTaskFragment_to_cleaningTaskFragment)

            }

            deleteAlert.setNegativeButton("No"){_, _ ->

            }
            deleteAlert.setTitle("Delete ${args.currentRecord.name}?")
            deleteAlert.setMessage("Please confirm that you want to delete ${args.currentRecord.name}")

            deleteAlert.create().show()

        }
        return super.onOptionsItemSelected(item)
    }
}