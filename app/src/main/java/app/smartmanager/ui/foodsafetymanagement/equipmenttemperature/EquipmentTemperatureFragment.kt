package app.smartmanager.ui.foodsafetymanagement.equipmenttemperature

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.ui.foodsafetymanagement.equipmenttemperature.viewmodel.EquipmentTemperatureViewModel

class EquipmentTemperatureFragment : Fragment() {

    companion object {
        fun newInstance() = EquipmentTemperatureFragment()
    }

    private lateinit var viewModel: EquipmentTemperatureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.equipment_temperature_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EquipmentTemperatureViewModel::class.java)
        // TODO: Use the ViewModel
    }

}