package app.smartmanager.ui.reports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.adapters.*
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.reports.viewmodel.InventoryReportsViewModel

class InventoryReportsFragment : Fragment() {

    private lateinit var inventoryReportsViewModel: InventoryReportsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =  inflater.inflate(R.layout.fragment_inventory_reports, container, false)
       inventoryReportsViewModel =
           ViewModelProvider(this).get(InventoryReportsViewModel::class.java)


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


        cookingReportDailyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = cookingReportAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            cookingReportAdapter.setCookingRecordData(inventoryReportsViewModel.cookingReportDaily)
            ToastMaker.showToast("Daily Cooking Report Loaded", GetAppContext.appContext)
        }


        cookingReportWeeklyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = cookingReportAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            cookingReportAdapter.setCookingRecordData(inventoryReportsViewModel.cookingReportWeekly)
            ToastMaker.showToast("Weekly Cooking Report Loaded", GetAppContext.appContext)
        }

        cookingReportMonthlyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = cookingReportAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            cookingReportAdapter.setCookingRecordData(inventoryReportsViewModel.cookingReportMonthly)
            ToastMaker.showToast("Monthly Cooking Report Loaded", GetAppContext.appContext)
        }

        inventoryReportDailyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = dailyInventoryReportAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            dailyInventoryReportAdapter.setDailyInventoryRecordData(inventoryReportsViewModel.inventoryReportDaily)
            ToastMaker.showToast("Daily Inventory Report Loaded", GetAppContext.appContext)

        }
        inventoryReportWeeklyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = dailyInventoryReportAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            dailyInventoryReportAdapter.setDailyInventoryRecordData(inventoryReportsViewModel.inventoryReportWeekly)
            ToastMaker.showToast("Weekly Inventory Report Loaded", GetAppContext.appContext)

        }
        inventoryReportMonthlyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = dailyInventoryReportAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            dailyInventoryReportAdapter.setDailyInventoryRecordData(inventoryReportsViewModel.inventoryReportMonthly)
            ToastMaker.showToast("Monthly Inventory Report Loaded", GetAppContext.appContext)
        }

        deliveryReportDailyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = deliveryReportAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            deliveryReportAdapter.setDeliveryRecordData(inventoryReportsViewModel.deliveryReportDaily)
            ToastMaker.showToast(
                "Daily Delivery Report Loaded",
                GetAppContext.appContext
            )
        }
        deliveryReportWeeklyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = deliveryReportAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            deliveryReportAdapter.setDeliveryRecordData(inventoryReportsViewModel.deliveryReportWeekly)
            ToastMaker.showToast(
                "Weekly Delivery Report Loaded",
                GetAppContext.appContext
            )

        }
        deliveryReportMonthlyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = deliveryReportAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            deliveryReportAdapter.setDeliveryRecordData(inventoryReportsViewModel.deliveryReportMonthly)
            ToastMaker.showToast(
                "Monthly Delivery Report Loaded",
                GetAppContext.appContext
            )
        }

        foodWasteReportDailyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = foodWasteReportAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            foodWasteReportAdapter.setFoodWasteRecordData(inventoryReportsViewModel.foodWasteReportDaily)
            ToastMaker.showToast(
                "Daily Food Wastage Report Loaded",
                GetAppContext.appContext
            )

        }
        foodWasteReportWeeklyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = foodWasteReportAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            foodWasteReportAdapter.setFoodWasteRecordData(inventoryReportsViewModel.foodWasteReportWeekly)
            ToastMaker.showToast(
                "Weekly CFood Wastage Report Loaded",
                GetAppContext.appContext
            )
        }
        foodWasteReportMonthlyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = foodWasteReportAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            foodWasteReportAdapter.setFoodWasteRecordData(inventoryReportsViewModel.foodWasteReportMonthly)
            ToastMaker.showToast(
                "Monthly Food Wastage Report Loaded",
                GetAppContext.appContext
            )
        }









        return fragmentView
    }

}