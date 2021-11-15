package app.smartmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.smartmanager.databinding.HomeScreenFragmentBinding
import app.smartmanager.ui.auth.LoginViewModel
import app.smartmanager.ui.auth.LoginViewModelFactory
import androidx.annotation.NonNull
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn

import com.google.android.gms.tasks.OnCompleteListener





class HomeScreen : Fragment() {

    private var binding: HomeScreenFragmentBinding? = null
    private val viewBinding get() = binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeScreenFragmentBinding.inflate(inflater, container, false)

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