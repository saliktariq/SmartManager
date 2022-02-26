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
import app.smartmanager.ui.setup.viewmodel.EquipmentViewModel
import app.smartmanager.ui.setup.viewmodel.SupplierViewModel
import kotlin.properties.Delegates


class UpdateEquipmentFragment : Fragment() {
    private lateinit var equipmentViewModel: EquipmentViewModel
    private val args by navArgs<UpdateEquipmentFragmentArgs>()

    // Variables to hold fragment data
    var equipmentID by Delegates.notNull<Long>()
    lateinit var equipmentName: String //This property can not be null as per our entity definition



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val fragmentView = inflater.inflate(R.layout.fragment_update_equipment, container, false)

        // Initialising the equipmentViewModel
        equipmentViewModel = ViewModelProvider(this).get(EquipmentViewModel::class.java)

        //Populating the Fragment fields with received arguments
        fragmentView.findViewById<EditText>(R.id.equipmentName).setText(args.currentRecord.name).toString()


        val updateButton: Button = fragmentView.findViewById(R.id.btnUpdate)

        //Setting onClickListener for update button
        updateButton.setOnClickListener {

            //Initialising fragment data variables with updated  entered data
            equipmentID = args.currentRecord.id.toLong()
            equipmentName = fragmentView.findViewById<EditText>(R.id.equipmentName).text.toString()


            val updateData = equipmentViewModel.updateData(equipmentID, equipmentName)

            if(updateData){
                //If updateData operation is successful navigate back to Equipment Fragment
                findNavController().navigate(R.id.action_updateEquipmentFragment_to_equipmentFragment)
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

                equipmentViewModel.deleteEquipment(args.currentRecord)
                ToastMaker.showToast("${args.currentRecord.name} deleted!", GetAppContext.appContext)

                findNavController().navigate(R.id.action_updateEquipmentFragment_to_equipmentFragment)

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