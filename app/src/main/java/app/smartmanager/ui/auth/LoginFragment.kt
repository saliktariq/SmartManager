package app.smartmanager.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.databinding.LoginFragmentBinding
import com.google.android.gms.common.SignInButton

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
        viewBinding.googleSignInButton.setSize(SignInButton.SIZE_WIDE)

        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Destroying the view binding
        binding = null
    }

}