package app.smartmanager.ui.inventorymanagement.deliveryrecord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.ui.inventorymanagement.deliveryrecord.viewmodel.DeliveryRecordViewModel

class DeliveryRecordFragment : Fragment() {

    companion object {
        fun newInstance() = DeliveryRecordFragment()
    }

    private lateinit var viewModel: DeliveryRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.delivery_record_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeliveryRecordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}