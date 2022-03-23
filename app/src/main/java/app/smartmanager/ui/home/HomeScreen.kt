package app.smartmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.smartmanager.databinding.HomeScreenFragmentBinding
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import app.smartmanager.R
import app.smartmanager.ui.home.viewmodel.HomeScreenViewModel
import app.smartmanager.ui.home.viewmodel.HomeScreenViewModelFactory


class HomeScreen : Fragment() {

    private var binding: HomeScreenFragmentBinding? = null
    private val viewBinding get() = binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeScreenFragmentBinding.inflate(inflater, container, false)


        // Variables to hold HomeScreenViewModel reference and HomeScreenViewModelFactory
        // Viewmodel not used in the class. Implemented for completion purpose and possible future development
        val viewModelFactory = HomeScreenViewModelFactory()
        val homeScreenViewModel = ViewModelProvider(this, viewModelFactory).get(HomeScreenViewModel::class.java)



        viewBinding.btnFoodSafetyManagement.setOnClickListener {
            viewBinding.root.findNavController().navigate(HomeScreenDirections.actionHomeScreenToFoodSafetyManagementHomeFragment())
        }

        viewBinding.btnInventoryControl.setOnClickListener {
            viewBinding.root.findNavController().navigate(HomeScreenDirections.actionHomeScreenToInventoryManagementHomeFragment())
        }

        viewBinding.btnReports.setOnClickListener {
            viewBinding.root.findNavController().navigate(HomeScreenDirections.actionHomeScreenToReportsHomeFragment())
        }

        viewBinding.btnSettings.setOnClickListener {
            viewBinding.root.findNavController().navigate(HomeScreenDirections.actionHomeScreenToInitialSettings())
        }
        viewBinding.btnLogOut.setOnClickListener{
            signOut()
        }




        return binding!!.root
    }

    private fun signOut(){
        viewBinding.root.findNavController().navigate(HomeScreenDirections.actionGlobalLoginFragment())


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}