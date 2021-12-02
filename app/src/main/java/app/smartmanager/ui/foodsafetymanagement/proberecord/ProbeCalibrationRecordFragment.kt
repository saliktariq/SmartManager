package app.smartmanager.ui.foodsafetymanagement.proberecord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import app.smartmanager.R
import app.smartmanager.ui.foodsafetymanagement.proberecord.viewmodel.ProbeCalibrationRecordViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class ProbeCalibrationRecordFragment : Fragment() {

    private lateinit var probeCalibrationRecordViewModel: ProbeCalibrationRecordViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView =  inflater.inflate(R.layout.probe_calibration_record_fragment, container, false)
        val floatingAddButton = fragmentView.findViewById<FloatingActionButton>(R.id.btnAddPCR)
        val backButton = fragmentView.findViewById<Button>(R.id.btnBackPCR)


        probeCalibrationRecordViewModel = ViewModelProvider(this).get(ProbeCalibrationRecordViewModel::class.java)

        floatingAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_probeCalibrationRecordFragment2_to_addProbeCalibrationRecordFragment)
        }

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_probeCalibrationRecordFragment2_to_foodSafetyManagementHomeFragment)
        }

        return fragmentView




    }


}