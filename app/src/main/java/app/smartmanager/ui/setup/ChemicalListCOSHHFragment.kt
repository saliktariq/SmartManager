package app.smartmanager.ui.setup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.ui.setup.viewmodel.ChemicalListCOSHHViewModel

class ChemicalListCOSHHFragment : Fragment() {

    companion object {
        fun newInstance() = ChemicalListCOSHHFragment()
    }

    private lateinit var viewModel: ChemicalListCOSHHViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chemical_list_coshh_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChemicalListCOSHHViewModel::class.java)
        // TODO: Use the ViewModel
    }

}