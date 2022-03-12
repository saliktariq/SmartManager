package app.smartmanager.ui.inventorymanagement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import app.smartmanager.R


class InventoryManagementHomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView =  inflater.inflate(R.layout.fragment_inventory_management_home, container, false)

        val btnCookedFoodRecord: Button = fragmentView.findViewById<Button>(R.id.btnCookedFoodRecord)
        val btnDailyInventoryRecord: Button = fragmentView.findViewById<Button>(R.id.btnDailyInventoryRecord)
        val btnDeliveryRecord: Button = fragmentView.findViewById<Button>(R.id.btnDeliveryRecord)
        val btnFoodWastageRecord: Button = fragmentView.findViewById<Button>(R.id.btnFoodWastageRecord)
        val backButton: Button = fragmentView.findViewById<Button>(R.id.btnBack)

        btnCookedFoodRecord.setOnClickListener{
            findNavController().navigate(R.id.action_inventoryManagementHomeFragment_to_cookingRecordFragment)
        }

        btnDailyInventoryRecord.setOnClickListener {
            findNavController().navigate(R.id.action_inventoryManagementHomeFragment_to_dailyInventoryFragment)
        }

        btnDeliveryRecord.setOnClickListener {
            findNavController().navigate(R.id.action_inventoryManagementHomeFragment_to_deliveryRecordFragment)
        }

        btnFoodWastageRecord.setOnClickListener {
            findNavController().navigate(R.id.action_inventoryManagementHomeFragment_to_foodWasteRecordFragment)
        }


        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_inventoryManagementHomeFragment_to_homeScreen)
        }
        return fragmentView
    }
}