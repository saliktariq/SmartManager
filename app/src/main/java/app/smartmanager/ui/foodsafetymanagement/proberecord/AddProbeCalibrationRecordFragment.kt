package app.smartmanager.ui.foodsafetymanagement.proberecord

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import app.smartmanager.R
import app.smartmanager.ui.foodsafetymanagement.proberecord.viewmodel.ProbeCalibrationRecordViewModel
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
        val fragmentView =  inflater.inflate(R.layout.fragment_add_probe_calibration_record, container, false)

        probeCalibrationRecordViewModel = ViewModelProvider(this).get(ProbeCalibrationRecordViewModel::class.java)



        //Defining spinner variables to access spinners on the UI
        val chooseProbeSpinner: Spinner = fragmentView.findViewById<Spinner>(R.id.spinner_probeNamePCR)
        val calibrationMethodSpinner: Spinner = fragmentView.findViewById<Spinner>(R.id.spinner_probeCalibrationMethod)

        //Setting arrayAdapter for calibrationMethodSpinner
        val calibrationMethodAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item, calibrationMethod
        )


        //Setting arrayAdapter for chooseProbeSpinner

        val chooseProbeAdapter = ArrayAdapter<String>(
                this.requireActivity(),
                android.R.layout.simple_spinner_item, probeCalibrationRecordViewModel.getListOfProbes()
            )


//        chooseProbeAdapter.addAll(probeCalibrationRecordViewModel.getListOfProbes())
//        chooseProbeAdapter.notifyDataSetChanged();

        //Setting drop down view resource to adapters
        calibrationMethodAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        chooseProbeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //attaching adapters to spinners
        calibrationMethodSpinner.setAdapter(calibrationMethodAdapter)
        chooseProbeSpinner.setAdapter(chooseProbeAdapter)






        val addButton: Button = fragmentView.findViewById(R.id.btnAddNewPCR)


        return fragmentView
    }



}