package app.smartmanager.ui.foodsafetymanagement.producttemperature

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.ui.foodsafetymanagement.producttemperature.viewmodel.CookedProductTemperatureViewModel

class CookedProductTemperatureFragment : Fragment() {

    companion object {
        fun newInstance() = CookedProductTemperatureFragment()
    }

    private lateinit var viewModelCooked: CookedProductTemperatureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cooked_product_temperature_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelCooked = ViewModelProvider(this).get(CookedProductTemperatureViewModel::class.java)
        // TODO: Use the ViewModel
    }

}