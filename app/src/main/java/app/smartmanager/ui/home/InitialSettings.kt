package app.smartmanager.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import app.smartmanager.R
import app.smartmanager.databinding.HomeScreenFragmentBinding
import app.smartmanager.databinding.InitialSettingsFragmentBinding

class InitialSettings : Fragment() {

    private var binding: InitialSettingsFragmentBinding? = null
    private val viewBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InitialSettingsFragmentBinding.inflate(inflater, container, false)

        //Attaching singOut function to Logout button
        viewBinding.btnLogOut.setOnClickListener{
            signOut()
        }

        //Setting On Click listener on AddNewProbe button
        viewBinding.addNewProbeButton.setOnClickListener {
            viewBinding.root.findNavController().navigate(InitialSettingsDirections.actionInitialSettingsToProbeFragment())
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