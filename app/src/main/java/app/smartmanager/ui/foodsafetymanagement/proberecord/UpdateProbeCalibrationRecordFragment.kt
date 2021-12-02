package app.smartmanager.ui.foodsafetymanagement.proberecord

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import app.smartmanager.R
import app.smartmanager.ui.foodsafetymanagement.proberecord.viewmodel.ProbeCalibrationRecordViewModel

class UpdateProbeCalibrationRecordFragment : Fragment() {

    private lateinit var probeCalibrationRecordViewModel: ProbeCalibrationRecordViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =  inflater.inflate(R.layout.fragment_update_probe_calibration_record, container, false)

        probeCalibrationRecordViewModel = ViewModelProvider(this).get(ProbeCalibrationRecordViewModel::class.java)
        return fragmentView
    }
}