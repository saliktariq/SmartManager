package app.smartmanager.ui.foodsafetymanagement.proberecord

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.smartmanager.R
import app.smartmanager.ui.foodsafetymanagement.proberecord.viewmodel.ProbeCalibrationRecordViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime.now
import java.util.*


class AddProbeCalibrationRecordFragment : Fragment() {

    private lateinit var probeCalibrationRecordViewModel: ProbeCalibrationRecordViewModel

    /*
    Setting variable of type array to hold choices for calibration method spinners
     */
    private var calibrationMethod = arrayOf("Boiling Water", "Frozen Ice")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =
            inflater.inflate(R.layout.fragment_add_probe_calibration_record, container, false)

        probeCalibrationRecordViewModel =
            ViewModelProvider(this).get(ProbeCalibrationRecordViewModel::class.java)


        //Defining spinner variables to access spinners on the UI
        val chooseProbeSpinner: Spinner =
            fragmentView.findViewById<Spinner>(R.id.spinner_probeNamePCR)
        val calibrationMethodSpinner: Spinner =
            fragmentView.findViewById<Spinner>(R.id.spinner_probeCalibrationMethod)

        //Setting arrayAdapter for calibrationMethodSpinner
        val calibrationMethodAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item, calibrationMethod
        )


        //Arraylist to hold values retrieved from DB for probe name
        var probeNameDataRetrieved = ArrayList<String>()


        //Setting arrayAdapter for chooseProbeSpinner

        val chooseProbeAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item, probeNameDataRetrieved
        )


//        chooseProbeAdapter.addAll(probeCalibrationRecordViewModel.getListOfProbes())
//        chooseProbeAdapter.notifyDataSetChanged();

        //Setting drop down view resource to adapters
        calibrationMethodAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        chooseProbeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line) //check  this if not working

        //attaching adapters to spinners
        calibrationMethodSpinner.setAdapter(calibrationMethodAdapter)
        chooseProbeSpinner.setAdapter(chooseProbeAdapter)


        probeCalibrationRecordViewModel.getAllProbeNames.observe(viewLifecycleOwner) { listOfProbes ->

            for (name in listOfProbes) {
                probeNameDataRetrieved.add(name.toString())
            }
            chooseProbeAdapter.notifyDataSetChanged()
        }

//        //Creating simple date object
//        // Used this link to learn to create this object https://stackoverflow.com/questions/47006254/how-to-get-current-local-date-and-time-in-kotlin
//        val dateTimeFormatObject = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
//        val currentDateTime = dateTimeFormatObject.format(Date())


        val addButton: Button = fragmentView.findViewById(R.id.btnAddNewPCR)

        addButton.setOnClickListener {
            val probe: String =
                fragmentView.findViewById<AppCompatSpinner>(R.id.spinner_probeNamePCR).selectedItem.toString()
            val temperature: Float =
                fragmentView.findViewById<EditText>(R.id.et_probeTemperature).text.toString()
                    .toFloat()
            val calibrationMethod: String =
                fragmentView.findViewById<AppCompatSpinner>(R.id.spinner_probeCalibrationMethod).selectedItem.toString()
            val date: Date = Calendar.getInstance().time
            val insertData = probeCalibrationRecordViewModel.insertData(probe, temperature, calibrationMethod, date)

            if(insertData){
                findNavController().navigate(R.id.action_addProbeCalibrationRecordFragment_to_probeCalibrationRecordFragment2)
            }


        }


        return fragmentView
    }


}