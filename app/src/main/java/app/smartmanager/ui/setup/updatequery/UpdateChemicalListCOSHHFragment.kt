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
import app.smartmanager.ui.setup.viewmodel.ChemicalListCOSHHViewModel
import app.smartmanager.ui.setup.viewmodel.CleaningTaskViewModel
import app.smartmanager.ui.setup.viewmodel.SupplierViewModel
import kotlin.properties.Delegates


class UpdateChemicalListCOSHHFragment : Fragment() {

    private lateinit var chemicalListCOSHHViewModel: ChemicalListCOSHHViewModel
    private val args by navArgs<UpdateChemicalListCOSHHFragmentArgs>()

    //Variables to hold fragment data
    var chemicalListCOSHHID by Delegates.notNull<Long>()
    lateinit var chemicalName: String //This property can not be null as per our entity definition
    var chemicalPurpose: String? = null
    var chemicalConcentration: String? = null
    var notes: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =  inflater.inflate(R.layout.fragment_update_chemical_list_coshh, container, false)
        // Initialising the chemicalListCOSHHViewModel
        chemicalListCOSHHViewModel = ViewModelProvider(this).get(ChemicalListCOSHHViewModel::class.java)

        //Populating the Fragment fields with received arguments
        fragmentView.findViewById<EditText>(R.id.chemicalName).setText(args.currentRecord.name).toString()
        fragmentView.findViewById<EditText>(R.id.chemicalPurpose).setText(args.currentRecord.purpose).toString()
        fragmentView.findViewById<EditText>(R.id.chemicalConcentration).setText(args.currentRecord.concentration).toString()
        fragmentView.findViewById<EditText>(R.id.notes).setText(args.currentRecord.notes).toString()


        val updateButton: Button = fragmentView.findViewById(R.id.btnUpdate)

        //Setting onClickListener for update button
        updateButton.setOnClickListener {

            //Initialising fragment data variables with updated  entered data
            chemicalListCOSHHID = args.currentRecord.id.toLong()
            chemicalName = fragmentView.findViewById<EditText>(R.id.chemicalName).text.toString()
            chemicalPurpose = fragmentView.findViewById<EditText>(R.id.chemicalPurpose).text.toString()
            chemicalConcentration = fragmentView.findViewById<EditText>(R.id.chemicalConcentration).text.toString()
            notes = fragmentView.findViewById<EditText>(R.id.notes).text.toString()


            val updateData = chemicalListCOSHHViewModel.updateData(chemicalListCOSHHID, chemicalName, chemicalPurpose, chemicalConcentration, notes)


            if(updateData){
                //If updateData operation is successful navigate back to ChemicalListCOSHH Fragment
                findNavController().navigate(R.id.action_updateChemicalListCOSHHFragment_to_chemicalListCOSHHFragment)
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

                chemicalListCOSHHViewModel.deleteChemicalListCOSHH(args.currentRecord)
                ToastMaker.showToast("${args.currentRecord.name} deleted!", GetAppContext.appContext)

                findNavController().navigate(R.id.action_updateChemicalListCOSHHFragment_to_chemicalListCOSHHFragment)

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