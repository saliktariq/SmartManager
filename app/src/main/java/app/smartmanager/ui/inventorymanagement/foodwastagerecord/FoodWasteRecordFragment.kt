package app.smartmanager.ui.inventorymanagement.foodwastagerecord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.ui.inventorymanagement.foodwastagerecord.viewmodel.FoodWasteRecordViewModel

class FoodWasteRecordFragment : Fragment() {

    companion object {
        fun newInstance() = FoodWasteRecordFragment()
    }

    private lateinit var viewModel: FoodWasteRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.food_waste_record_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodWasteRecordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}