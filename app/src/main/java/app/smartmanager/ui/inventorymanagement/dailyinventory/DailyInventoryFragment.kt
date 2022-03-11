package app.smartmanager.ui.inventorymanagement.dailyinventory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.ui.inventorymanagement.dailyinventory.viewmodel.DailyInventoryViewModel

class DailyInventoryFragment : Fragment() {

    companion object {
        fun newInstance() = DailyInventoryFragment()
    }

    private lateinit var viewModel: DailyInventoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.daily_inventory_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DailyInventoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}