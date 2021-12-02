package app.smartmanager.ui.foodsafetymanagement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import app.smartmanager.R


class FoodSafetyManagementHomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =  inflater.inflate(R.layout.fragment_food_safety_management_home, container, false)

        val addProbeCalibrationRecordButton: Button = fragmentView.findViewById<Button>(R.id.btnAddProbeCalibrationData)
        val backButton: Button = fragmentView.findViewById<Button>(R.id.btnBack)

        addProbeCalibrationRecordButton.setOnClickListener{
            findNavController().navigate(R.id.action_foodSafetyManagementHomeFragment_to_probeCalibrationRecordFragment2)
        }

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_foodSafetyManagementHomeFragment_to_homeScreen)
        }
        return fragmentView
    }

}