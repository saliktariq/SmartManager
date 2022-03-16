package app.smartmanager.ui.reports

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R

import app.smartmanager.adapters.CleaningReportsAdapter
import app.smartmanager.adapters.DonenessTestReportsAdapter
import app.smartmanager.adapters.EquipmentTemperatureReportsAdapter
import app.smartmanager.adapters.ProbeCalibrationReportsAdapter
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.reports.viewmodel.FoodSafetyReportsViewModel

class FoodSafetyReportsFragment : Fragment() {

    private lateinit var foodSafetyReportsViewModel: FoodSafetyReportsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView =
            inflater.inflate(R.layout.food_safety_management_reports_fragment, container, false)

        foodSafetyReportsViewModel = ViewModelProvider(this).get(FoodSafetyReportsViewModel::class.java)

        val cleaningAdapter = CleaningReportsAdapter()
        val probeAdapter = ProbeCalibrationReportsAdapter()
        val equipmentTemperatureAdapter = EquipmentTemperatureReportsAdapter()
        val donenessTestAdapter = DonenessTestReportsAdapter()

        val recyclerView = fragmentView.findViewById<RecyclerView>(R.id.showReportView)

        val probeCalibrationDailyButton: AppCompatButton =
            fragmentView.findViewById(R.id.probe_report_daily)
        val probeCalibrationWeeklyButton: AppCompatButton =
            fragmentView.findViewById(R.id.probe_report_weekly)
        val probeCalibrationMonthlyButton: AppCompatButton =
            fragmentView.findViewById(R.id.probe_report_monthly)

        val cleaningReportDailyButton: AppCompatButton =
            fragmentView.findViewById(R.id.cleaning_report_daily)
        val cleaningReportWeeklyButton: AppCompatButton =
            fragmentView.findViewById(R.id.cleaning_report_weekly)
        val cleaningReportMonthlyButton: AppCompatButton =
            fragmentView.findViewById(R.id.cleaning_report_monthly)

        val equipmentTemperatureDailyButton: AppCompatButton =
            fragmentView.findViewById(R.id.equip_report_daily)
        val equipmentTemperatureWeeklyButton: AppCompatButton =
            fragmentView.findViewById(R.id.equip_report_weekly)
        val equipmentTemperatureMonthlyButton: AppCompatButton =
            fragmentView.findViewById(R.id.equip_report_monthly)

        val donenessTestDailyButton: AppCompatButton =
            fragmentView.findViewById(R.id.doneness_report_daily)
        val donenessTestWeeklyButton: AppCompatButton =
            fragmentView.findViewById(R.id.doneness_report_weekly)
        val donenessTestMonthlyButton: AppCompatButton =
            fragmentView.findViewById(R.id.doneness_report_monthly)

        probeCalibrationDailyButton.setOnClickListener {
        }
        //Implementing recyclerview
        recyclerView.adapter = probeAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        probeAdapter.setDataSet(foodSafetyReportsViewModel.probeCalibrationDaily)
        ToastMaker.showToast("Daily Probe Report Loaded", GetAppContext.appContext)

        probeCalibrationWeeklyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = probeAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            probeAdapter.setDataSet(foodSafetyReportsViewModel.probeCalibrationWeekly)
            ToastMaker.showToast("Weekly Probe Report Loaded", GetAppContext.appContext)
        }

        probeCalibrationMonthlyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = probeAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            probeAdapter.setDataSet(foodSafetyReportsViewModel.probelCalibrationMonthly)
            ToastMaker.showToast("Monthly Probe Report Loaded", GetAppContext.appContext)
        }

        cleaningReportDailyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = cleaningAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            cleaningAdapter.setDataSet(foodSafetyReportsViewModel.cleaningReportDaily)
            ToastMaker.showToast("Daily Cleaning Report Loaded", GetAppContext.appContext)

        }
        cleaningReportWeeklyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = cleaningAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            cleaningAdapter.setDataSet(foodSafetyReportsViewModel.cleaningReportWeekly)
            ToastMaker.showToast("Weekly Cleaning Report Loaded", GetAppContext.appContext)

//            reportsViewModel.cleaningReportWeekly.observe(viewLifecycleOwner, Observer { data ->
//                adapter.setDataSet(data)
//            })
        }
        cleaningReportMonthlyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = cleaningAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            cleaningAdapter.setDataSet(foodSafetyReportsViewModel.cleaningReportMonthly)
            ToastMaker.showToast("Monthly Cleaning Report Loaded", GetAppContext.appContext)
        }

        equipmentTemperatureDailyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = equipmentTemperatureAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            equipmentTemperatureAdapter.setDataSet(foodSafetyReportsViewModel.equipmentTemperatureDaily)
            ToastMaker.showToast(
                "Daily Equipment Temperature Report Loaded",
                GetAppContext.appContext
            )
        }
        equipmentTemperatureWeeklyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = equipmentTemperatureAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            equipmentTemperatureAdapter.setDataSet(foodSafetyReportsViewModel.equipmentTemperatureWeekly)
            ToastMaker.showToast(
                "Weekly Equipment Temperature Report Loaded",
                GetAppContext.appContext
            )

        }
        equipmentTemperatureMonthlyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = equipmentTemperatureAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            equipmentTemperatureAdapter.setDataSet(foodSafetyReportsViewModel.equipmentTemperatureMonthly)
            ToastMaker.showToast(
                "Monthly Equipment Temperature Report Loaded",
                GetAppContext.appContext
            )
        }

        donenessTestDailyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = donenessTestAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            donenessTestAdapter.setDataSet(foodSafetyReportsViewModel.donenessTestDaily)
            ToastMaker.showToast(
                "Daily Cooking Temperature Report Loaded",
                GetAppContext.appContext
            )

        }
        donenessTestWeeklyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = donenessTestAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            donenessTestAdapter.setDataSet(foodSafetyReportsViewModel.donenessTestWeekly)
            ToastMaker.showToast(
                "Weekly Cooking Temperature Report Loaded",
                GetAppContext.appContext
            )
        }
        donenessTestMonthlyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = donenessTestAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            donenessTestAdapter.setDataSet(foodSafetyReportsViewModel.donenessTestMonthly)
            ToastMaker.showToast(
                "Monthly Cooking Temperature Report Loaded",
                GetAppContext.appContext
            )
        }
        return fragmentView
    }


}