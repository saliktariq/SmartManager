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
import app.smartmanager.ui.setup.viewmodel.ControlChecksViewModel
import app.smartmanager.ui.setup.viewmodel.SupplierViewModel
import kotlin.properties.Delegates


class UpdateControlChecksFragment : Fragment() {
    private lateinit var controlChecksViewModel: ControlChecksViewModel
    private val args by navArgs<UpdateControlChecksFragmentArgs>()

    //Variables to hold fragment data

    var controlCheckID by Delegates.notNull<Long>()
    lateinit var controlName: String //This property can not be null as per our entity definition
    var controlDescription: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =  inflater.inflate(R.layout.fragment_update_control_checks, container, false)

        //Initialising the ControlChecksViewModel
        controlChecksViewModel = ViewModelProvider(this).get(ControlChecksViewModel::class.java)

        //Populating the Fragment fields with received arguments
        fragmentView.findViewById<EditText>(R.id.controlName).setText(args.currentRecord.name).toString()



        val updateButton: Button = fragmentView.findViewById(R.id.btnUpdate)

        //Setting onClickListener for the update button
        updateButton.setOnClickListener {

            //Initialising fragment data variables with updated  entered data
            controlCheckID = args.currentRecord.id.toLong()
            controlName = fragmentView.findViewById<EditText>(R.id.controlName).text.toString()
            controlDescription = fragmentView.findViewById<EditText>(R.id.controlDescription).text.toString()

            val updateData = controlChecksViewModel.updateData(controlCheckID,controlName,controlDescription)

            if(updateData){
                //If updateData operation is successful navigate back to ControlChecks Fragment
                findNavController().navigate(R.id.action_updateControlChecksFragment_to_controlChecksFragment)
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

                controlChecksViewModel.deleteControlChecks(args.currentRecord)
                ToastMaker.showToast("${args.currentRecord.name} deleted!", GetAppContext.appContext)

                findNavController().navigate(R.id.action_updateControlChecksFragment_to_controlChecksFragment)

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