package app.smartmanager.ui.initialsetup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope

import app.smartmanager.databinding.ProbeFragmentBinding
import app.smartmanager.helper.GetAppContext
import app.smartmanager.helper.ToastMaker
import kotlinx.coroutines.launch


class ProbeFragment : Fragment() {
    private var binding: ProbeFragmentBinding? = null
    private val viewBinding get() = binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProbeFragmentBinding.inflate(inflater, container, false)


        // Variables to hold ProbeViewModel and ProbeViewModelFactory
        val viewModelFactory = ProbeViewModelFactory()
        val probeViewModel = ViewModelProvider(this, viewModelFactory).get(ProbeViewModel::class.java)

        // Setting onClickListener to btnAddData
        viewBinding.btnAddData.setOnClickListener {

            if (viewBinding.probeName.text.toString().length < 2){
                ToastMaker.showToast("Probe name should be greater than 1 character",GetAppContext.appContext)
            } else {
                //Setting viewModel variables as per entered data by user
                probeViewModel.probeName = viewBinding.probeName.text.toString()

                //Starting coroutine with application lifecycle scope
                viewLifecycleOwner.lifecycleScope.launch {

                    probeViewModel.insertProbeData(probeViewModel.probeName)

                }
            }




        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}