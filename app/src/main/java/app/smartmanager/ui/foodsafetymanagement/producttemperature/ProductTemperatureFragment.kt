package app.smartmanager.ui.foodsafetymanagement.producttemperature

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.ui.foodsafetymanagement.producttemperature.viewmodel.ProductTemperatureViewModel

class ProductTemperatureFragment : Fragment() {

    companion object {
        fun newInstance() = ProductTemperatureFragment()
    }

    private lateinit var viewModel: ProductTemperatureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.probe_temperature_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductTemperatureViewModel::class.java)
        // TODO: Use the ViewModel
    }

}