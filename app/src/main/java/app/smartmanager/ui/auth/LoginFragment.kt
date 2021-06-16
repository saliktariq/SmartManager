package app.smartmanager.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private var binding: LoginFragmentBinding? = null
    private val viewBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        val viewModelFactory = LoginViewModelFactory()
        val loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Destroying the view binding
        binding = null
    }

}