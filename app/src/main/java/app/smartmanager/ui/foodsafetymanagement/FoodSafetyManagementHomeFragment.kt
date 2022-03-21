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

        val btnProbeCalibrationRecord: Button = fragmentView.findViewById<Button>(R.id.btnProbeCalibrationRecord)
        val btnAddCleaningRecord: Button = fragmentView.findViewById<Button>(R.id.btnAddCleaningRecord)
        val btnEquipmentTemperatureRecord: Button = fragmentView.findViewById<Button>(R.id.btnEquipmentTemperatureRecord)
        val btnCookedProductTemperatureRecord: Button = fragmentView.findViewById<Button>(R.id.btnCookedProductTemperatureRecord)
        val btnStaffTrainingRecord: Button = fragmentView.findViewById(R.id.btnStaffTrainingRecord)
        val backButton: Button = fragmentView.findViewById<Button>(R.id.btnBack)

        btnProbeCalibrationRecord.setOnClickListener{
            findNavController().navigate(R.id.action_foodSafetyManagementHomeFragment_to_probeCalibrationRecordFragment2)
        }

        btnAddCleaningRecord.setOnClickListener {
            findNavController().navigate(R.id.action_foodSafetyManagementHomeFragment_to_cleaningRecordFragment)
        }

        btnEquipmentTemperatureRecord.setOnClickListener {
            findNavController().navigate(R.id.action_foodSafetyManagementHomeFragment_to_equipmentTemperatureFragment)
        }

        btnCookedProductTemperatureRecord.setOnClickListener {
            findNavController().navigate(R.id.action_foodSafetyManagementHomeFragment_to_cookedProductTemperatureFragment)
        }

        btnStaffTrainingRecord.setOnClickListener{
            findNavController().navigate(R.id.action_foodSafetyManagementHomeFragment_to_staffTrainingRecordFragment)
        }

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_foodSafetyManagementHomeFragment_to_homeScreen)
        }
        return fragmentView
    }

}