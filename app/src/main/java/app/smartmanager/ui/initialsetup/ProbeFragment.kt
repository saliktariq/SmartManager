package app.smartmanager.ui.initialsetup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import app.smartmanager.R
import app.smartmanager.databinding.HomeScreenFragmentBinding
import app.smartmanager.databinding.ProbeFragmentBinding
import app.smartmanager.ui.home.HomeScreenDirections
import app.smartmanager.ui.home.HomeScreenViewModel
import app.smartmanager.ui.home.HomeScreenViewModelFactory

class ProbeFragment : Fragment() {
    private var binding: ProbeFragmentBinding? = null
    private val viewBinding get() = binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProbeFragmentBinding.inflate(inflater, container, false)


        // Variables to hold HomeScreenViewModel reference and HomeScreenViewModelFactory
        val viewModelFactory = ProbeViewModelFactory()
        val probeViewModel = ViewModelProvider(this, viewModelFactory).get(ProbeViewModel::class.java)

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}