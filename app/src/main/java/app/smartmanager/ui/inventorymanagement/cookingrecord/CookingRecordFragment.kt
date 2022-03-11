package app.smartmanager.ui.inventorymanagement.cookingrecord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.ui.inventorymanagement.cookingrecord.viewmodel.CookingRecordViewModel

class CookingRecordFragment : Fragment() {

    companion object {
        fun newInstance() = CookingRecordFragment()
    }

    private lateinit var viewModel: CookingRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cooking_record_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CookingRecordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}