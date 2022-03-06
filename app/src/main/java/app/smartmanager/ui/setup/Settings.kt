package app.smartmanager.ui.setup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import app.smartmanager.databinding.InitialSettingsFragmentBinding

class Settings : Fragment() {

    private var binding: InitialSettingsFragmentBinding? = null
    private val viewBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InitialSettingsFragmentBinding.inflate(inflater, container, false)



        //Setting On Click listener on Add Probe button
        viewBinding.addNewProbeButton.setOnClickListener {
            viewBinding.root.findNavController().navigate(SettingsDirections.actionInitialSettingsToProbeFragment())
        }

        //Setting On Click listener on Add Supplier button
        viewBinding.addNewSupplierButton.setOnClickListener {
            viewBinding.root.findNavController().navigate(SettingsDirections.actionInitialSettingsToSupplierFragment())
        }

        //Setting On Click listener on Add Equipment button
        viewBinding.addNewEquipmentButton.setOnClickListener {
            viewBinding.root.findNavController().navigate(SettingsDirections.actionInitialSettingsToEquipmentFragment())
        }

        //Setting On Click listener on Add Control Check button
        viewBinding.addNewControlChecksButton.setOnClickListener {
            viewBinding.root.findNavController().navigate(SettingsDirections.actionInitialSettingsToControlChecksFragment())
        }

        //Setting On Click listener on Add Cleaning Task button
        viewBinding.addNewCleaningTaskButton.setOnClickListener {
            viewBinding.root.findNavController().navigate(SettingsDirections.actionInitialSettingsToCleaningTaskFragment())
        }

        //Setting On Click listener on Add COSHH Chemical button
        viewBinding.addNewChemicalButton.setOnClickListener {
            viewBinding.root.findNavController().navigate(SettingsDirections.actionInitialSettingsToChemicalListCOSHHFragment())
        }

        //Setting OnClickListener to btnBack
        viewBinding.btnBack.setOnClickListener {
            viewBinding.root.findNavController().navigate(SettingsDirections.actionInitialSettingsToHomeScreen())
        }

        //Attaching singOut function to Logout button
        viewBinding.btnLogOut.setOnClickListener{
            signOut()
        }

        return binding!!.root
    }

    private fun signOut(){

     viewBinding.root.findNavController().navigate(SettingsDirections.actionGlobalLoginFragment())


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}