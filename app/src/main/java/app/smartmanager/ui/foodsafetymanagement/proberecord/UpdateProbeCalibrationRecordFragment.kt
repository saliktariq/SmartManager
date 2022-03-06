package app.smartmanager.ui.foodsafetymanagement.proberecord

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.smartmanager.R
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.foodsafetymanagement.proberecord.viewmodel.ProbeCalibrationRecordViewModel
import java.util.*
import kotlin.properties.Delegates

class UpdateProbeCalibrationRecordFragment : Fragment() {

    private lateinit var probeCalibrationRecordViewModel: ProbeCalibrationRecordViewModel
    private val args by navArgs<UpdateProbeCalibrationRecordFragmentArgs>()

    //Variables to hold fragment data
    var currentID by Delegates.notNull<Long>()
    lateinit var probe: String
    var temperature by Delegates.notNull<Float>()
    lateinit var calibrationMethodData: String
    lateinit var date: Date

    /*
Setting variable of type array to hold choices for calibration method spinners
 */
    private var calibrationMethod = arrayOf("Boiling Water", "Frozen Ice")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =  inflater.inflate(R.layout.fragment_update_probe_calibration_record, container, false)

        // Initialising the equipmentViewModel
        probeCalibrationRecordViewModel = ViewModelProvider(this).get(ProbeCalibrationRecordViewModel::class.java)

        //Defining spinner variables to access spinners on the UI
        val calibrationMethodSpinner: Spinner =
            fragmentView.findViewById<Spinner>(R.id.spinner_probeCalibrationMethod)

        //Setting arrayAdapter for calibrationMethodSpinner
        val calibrationMethodAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item, calibrationMethod
        )

        //Setting drop down view resource to adapters
        calibrationMethodAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //attaching adapters to spinners
        calibrationMethodSpinner.setAdapter(calibrationMethodAdapter)





        //Populating the Fragment fields with received arguments
        fragmentView.findViewById<TextView>(R.id.spinner_probeNamePCR).setText(args.currentRecord.probe)
        fragmentView.findViewById<EditText>(R.id.et_probeTemperature).setText(args.currentRecord.temperature.toString())

        var calibrationMethodPosition: Int = 0

        if(args.currentRecord.calibrationMethod.equals("Boiling Water")){
            calibrationMethodPosition = 0
        } else {
            calibrationMethodPosition = 1
        }
        calibrationMethodSpinner.setSelection(calibrationMethodPosition)
val updateButton : Button = fragmentView.findViewById(R.id.btnUpdatePCR)

        updateButton.setOnClickListener {
            //Initialising fragment data variables with updated  entered data
            currentID = args.currentRecord.id
            probe = args.currentRecord.probe
            temperature = fragmentView.findViewById<EditText>(R.id.et_probeTemperature).text.toString().toFloat()
            calibrationMethodData = calibrationMethodSpinner.selectedItem.toString()
            date = Calendar.getInstance().time

            val updateData = probeCalibrationRecordViewModel.updateData(currentID, probe, temperature, calibrationMethodData, date)

            if(updateData){
                findNavController().navigate(R.id.action_updateProbeCalibrationRecordFragment_to_probeCalibrationRecordFragment2)
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

                probeCalibrationRecordViewModel.deleteProbeCalibrationRecord(args.currentRecord)
                ToastMaker.showToast("${args.currentRecord.probe} deleted!", GetAppContext.appContext)

                findNavController().navigate(R.id.action_updateProbeCalibrationRecordFragment_to_probeCalibrationRecordFragment2)

            }

            deleteAlert.setNegativeButton("No"){_, _ ->

            }
            deleteAlert.setTitle("Delete ${args.currentRecord.probe}?")
            deleteAlert.setMessage("Please confirm that you want to delete ${args.currentRecord.probe}")

            deleteAlert.create().show()

        }
        return super.onOptionsItemSelected(item)
    }
}