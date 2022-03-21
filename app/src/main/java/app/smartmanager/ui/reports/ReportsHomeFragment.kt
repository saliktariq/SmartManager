package app.smartmanager.ui.reports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import app.smartmanager.R
import app.smartmanager.adapters.CleaningRecordAdapter
import app.smartmanager.adapters.CookedProductTemperatureRecordAdapter
import app.smartmanager.adapters.EquipmentTemperatureRecordAdapter
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.datalayer.entity.CookedProductTemperatureRecord
import app.smartmanager.datalayer.entity.EquipmentTemperatureRecord


class ReportsHomeFragment : Fragment()
{


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_reports_home, container, false)

        val foodSafetyReportsButton: AppCompatButton = fragmentView.findViewById(R.id.btnFoodSafetyReports)
        val inventoryReportsButton: AppCompatButton = fragmentView.findViewById(R.id.btnInventoryReports)
        val backToHomeButton: AppCompatButton = fragmentView.findViewById(R.id.btnbackToHome)
        val signOutButton: AppCompatButton = fragmentView.findViewById(R.id.btnLogOut)

        foodSafetyReportsButton.setOnClickListener {
            findNavController().navigate(R.id.action_reportsHomeFragment_to_foodSafetyReportsFragment)
        }

        inventoryReportsButton.setOnClickListener {
            findNavController().navigate(R.id.action_reportsHomeFragment_to_inventoryReportsFragment)
        }

        backToHomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_reportsHomeFragment_to_homeScreen)
        }

        signOutButton.setOnClickListener {
            findNavController().navigate(R.id.action_global_loginFragment)
        }




        return fragmentView
    }



}