package app.smartmanager.ui.foodsafetymanagement.stafftrainingrecord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.ui.foodsafetymanagement.stafftrainingrecord.viewmodel.StaffTrainingRecordViewModel

class StaffTrainingRecordFragment : Fragment() {

    companion object {
        fun newInstance() = StaffTrainingRecordFragment()
    }

    private lateinit var viewModel: StaffTrainingRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.staff_training_record_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StaffTrainingRecordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}