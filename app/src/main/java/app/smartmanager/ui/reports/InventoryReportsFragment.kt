package app.smartmanager.ui.reports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.*
import app.smartmanager.ui.reports.viewmodel.InventoryReportsViewModel

class InventoryReportsFragment : Fragment() {

    private lateinit var inventoryReportsViewModel: InventoryReportsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =  inflater.inflate(R.layout.fragment_inventory_reports, container, false)
        inventoryReportsViewModel = ViewModelProvider(this).get(InventoryReportsViewModel::class.java)

        val cookingReportAdapter = CookingReportAdapter()
        val dailyInventoryReportAdapter = DailyInventoryReportAdapter()
        val deliveryReportAdapter = DeliveryReportAdapter()
        val foodWasteReportAdapter = FoodWasteReportAdapter()

        val recyclerView = fragmentView.findViewById<RecyclerView>(R.id.showReportView)

        val cookingReportDailyButton: AppCompatButton =
            fragmentView.findViewById(R.id.cooking_report_daily)
        val cookingReportWeeklyButton: AppCompatButton =
            fragmentView.findViewById(R.id.cooking_report_weekly)
        val cookingReportMonthlyButton: AppCompatButton =
            fragmentView.findViewById(R.id.cooking_report_monthly)

        val inventoryReportDailyButton: AppCompatButton =
            fragmentView.findViewById(R.id.inventory_report_daily)
        val inventoryReportWeeklyButton: AppCompatButton =
            fragmentView.findViewById(R.id.inventory_report_weekly)
        val inventoryReportMonthlyButton: AppCompatButton =
            fragmentView.findViewById(R.id.inventory_report_monthly)

        val deliveryReportDailyButton: AppCompatButton =
            fragmentView.findViewById(R.id.delivery_report_daily)
        val deliveryReportWeeklyButton: AppCompatButton =
            fragmentView.findViewById(R.id.delivery_report_weekly)
        val deliveryReportMonthlyButton: AppCompatButton =
            fragmentView.findViewById(R.id.delivery_report_monthly)

        val foodWasteReportDailyButton: AppCompatButton =
            fragmentView.findViewById(R.id.food_waste_report_daily)
        val foodWasteReportWeeklyButton: AppCompatButton =
            fragmentView.findViewById(R.id.food_waste_report_weekly)
        val foodWasteReportMonthlyButton: AppCompatButton =
            fragmentView.findViewById(R.id.food_waste_report_monthly)












        return fragmentView
    }

}