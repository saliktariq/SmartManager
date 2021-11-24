package app.smartmanager.ui.setup.updatequery

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.smartmanager.R
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.setup.viewmodel.SupplierViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class UpdateSupplierFragment : Fragment() {

    private lateinit var supplierViewModel: SupplierViewModel
    private val args by navArgs<UpdateSupplierFragmentArgs>()

    // Variables to hold fragment data
    var supplierID by Delegates.notNull<Long>()
    lateinit var supplierName: String //This property can not be null as per our entity definition
    var supplierEmail: String? = null
    var supplierPhone: String? = null
    var supplierAddress: String? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =  inflater.inflate(R.layout.fragment_update_supplier, container, false)

        // Initialising the supplierViewModel
        supplierViewModel = ViewModelProvider(this).get(SupplierViewModel::class.java)



        //Populating the Fragment fields with received arguments
        fragmentView.findViewById<EditText>(R.id.supplierName).setText(args.currentSupplier.name).toString()
        fragmentView.findViewById<EditText>(R.id.supplierEmail).setText(args.currentSupplier.email).toString()
        fragmentView.findViewById<EditText>(R.id.supplierPhone).setText(args.currentSupplier.phone).toString()
        fragmentView.findViewById<EditText>(R.id.supplierAddress).setText(args.currentSupplier.address).toString()

        //Setting onClickListener for update button
        val updateButton: Button = fragmentView.findViewById(R.id.btnUpdate)

        updateButton.setOnClickListener {

            //Initialising fragment data variables with updated  entered data
            supplierID = args.currentSupplier.id.toLong()
            supplierName = fragmentView.findViewById<EditText>(R.id.supplierName).text.toString()
            supplierEmail = fragmentView.findViewById<EditText>(R.id.supplierEmail).text.toString()
            supplierPhone = fragmentView.findViewById<EditText>(R.id.supplierPhone).text.toString()
            supplierAddress = fragmentView.findViewById<EditText>(R.id.supplierAddress).text.toString()

            val updateData = supplierViewModel.updateDataSetToRoomDB(supplierID, supplierName, supplierEmail, supplierPhone, supplierAddress)

            if(updateData){
                //If insertData operation is successful navigate back to Supplier Fragment
                findNavController().navigate(R.id.action_updateSupplierFragment_to_supplierFragment)
            }

        }

        // Adding menu to Update Supplier View
        setHasOptionsMenu(true)




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

                supplierViewModel.deleteSupplier(args.currentSupplier)
                ToastMaker.showToast("${args.currentSupplier.name} deleted!", GetAppContext.appContext)

                findNavController().navigate(R.id.action_updateSupplierFragment_to_supplierFragment)

            }

            deleteAlert.setNegativeButton("No"){_, _ ->

            }
            deleteAlert.setTitle("Delete ${args.currentSupplier.name}?")
            deleteAlert.setMessage("Please confirm that you want to delete ${args.currentSupplier.name}")

            deleteAlert.create().show()

        }
        return super.onOptionsItemSelected(item)
    }







}