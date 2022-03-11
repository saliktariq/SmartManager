package app.smartmanager.ui.foodsafetymanagement.cleaningrecord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.ui.foodsafetymanagement.cleaningrecord.viewmodel.CleaningRecordViewModel

class CleaningRecordFragment : Fragment() {

    companion object {
        fun newInstance() = CleaningRecordFragment()
    }

    private lateinit var viewModel: CleaningRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cleaning_record_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CleaningRecordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}