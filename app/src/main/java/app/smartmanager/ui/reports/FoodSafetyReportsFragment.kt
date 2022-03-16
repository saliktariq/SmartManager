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
import androidx.lifecycle.Observer

import app.smartmanager.adapters.CleaningReportsAdapter
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.reports.viewmodel.ReportsViewModel
import java.util.*

class FoodSafetyReportsFragment : Fragment() {

    private lateinit var reportsViewModel: ReportsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView =  inflater.inflate(R.layout.food_safety_management_reports_fragment, container, false)

        reportsViewModel = ViewModelProvider(this).get(ReportsViewModel::class.java)
        val cleaningAdapter = CleaningReportsAdapter()
        val recyclerView = fragmentView.findViewById<RecyclerView>(R.id.showReportView)

        val probeCalibrationDailyButton: AppCompatButton = fragmentView.findViewById(R.id.probe_report_daily)
        val probeCalibrationWeeklyButton: AppCompatButton = fragmentView.findViewById(R.id.probe_report_weekly)
        val probeCalibrationMonthlyButton: AppCompatButton = fragmentView.findViewById(R.id.probe_report_monthly)

        val cleaningReportDailyButton: AppCompatButton = fragmentView.findViewById(R.id.cleaning_report_daily)
        val cleaningReportWeeklyButton: AppCompatButton = fragmentView.findViewById(R.id.cleaning_report_weekly)
        val cleaningReportMonthlyButton: AppCompatButton = fragmentView.findViewById(R.id.cleaning_report_monthly)

        val equipmentTemperatureDailyButton: AppCompatButton = fragmentView.findViewById(R.id.equip_report_daily)
        val equipmentTemperatureWeeklyButton: AppCompatButton = fragmentView.findViewById(R.id.equip_report_weekly)
        val equipmentTemperatureMonthlyButton: AppCompatButton = fragmentView.findViewById(R.id.equip_report_monthly)

        val donenessTestDailyButton: AppCompatButton = fragmentView.findViewById(R.id.doneness_report_daily)
        val donenessTestWeeklyButton: AppCompatButton = fragmentView.findViewById(R.id.doneness_report_weekly)
        val donenessTestMonthlyButton: AppCompatButton = fragmentView.findViewById(R.id.doneness_report_monthly)

        probeCalibrationDailyButton.setOnClickListener {
        }

        probeCalibrationWeeklyButton.setOnClickListener {

        }

        probeCalibrationMonthlyButton.setOnClickListener {

        }

        cleaningReportDailyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = cleaningAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            cleaningAdapter.setDataSet(reportsViewModel.cleaningReportDaily)
            ToastMaker.showToast("Daily Report Loaded",GetAppContext.appContext)

        }
        cleaningReportWeeklyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = cleaningAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            cleaningAdapter.setDataSet(reportsViewModel.cleaningReportWeekly)
            ToastMaker.showToast("Weekly Report Loaded",GetAppContext.appContext)

//            reportsViewModel.cleaningReportWeekly.observe(viewLifecycleOwner, Observer { data ->
//                adapter.setDataSet(data)
//            })
        }
        cleaningReportMonthlyButton.setOnClickListener {
            //Implementing recyclerview
            recyclerView.adapter = cleaningAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            cleaningAdapter.setDataSet(reportsViewModel.cleaningReportMonthly)
            ToastMaker.showToast("Monthly Report Loaded",GetAppContext.appContext)
        }

        equipmentTemperatureDailyButton.setOnClickListener {
        }
        equipmentTemperatureWeeklyButton.setOnClickListener {

        }
        equipmentTemperatureMonthlyButton.setOnClickListener {

        }

        donenessTestDailyButton.setOnClickListener {
        }
        donenessTestWeeklyButton.setOnClickListener {

        }
        donenessTestMonthlyButton.setOnClickListener {

        }






        return fragmentView
    }



}