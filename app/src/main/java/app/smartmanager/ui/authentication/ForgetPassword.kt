package app.smartmanager.ui.authentication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import app.smartmanager.R
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.authentication.viewmodel.ForgetPasswordViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class ForgetPassword : Fragment() {

    private lateinit var forgetPasswordViewModel: ForgetPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.forget_password_fragment, container, false)
        forgetPasswordViewModel = ViewModelProvider(this).get(ForgetPasswordViewModel::class.java)
        val username: TextInputEditText = fragmentView.findViewById<TextInputEditText>(R.id.username)
        var requestPasswordButton: AppCompatButton = fragmentView.findViewById(R.id.buttonRequestPassword)
        var setNewPasswordTextView: AppCompatTextView = fragmentView.findViewById(R.id.btnSetNewPassword)
        var signUpButton: AppCompatTextView = fragmentView.findViewById(R.id.btnSignUp)

        signUpButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_registerNewAccount
            )
        }

        setNewPasswordTextView.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_updatePassword
            )
        }

        requestPasswordButton.setOnClickListener {
            forgetPasswordViewModel.emailAddress = username.text.toString()
            if (forgetPasswordViewModel.emailAddress.length < 3) { //The theoretical minimum length of any email address is 3
                ToastMaker.showToast("Enter valid email address", context)
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
                    val fetchData = viewLifecycleOwner.lifecycleScope.launch {
                        forgetPasswordViewModel.sendAuthCode()
                    }
                    //Fetch data and update variable isValidEmail before checking this variable in condition
                    fetchData.join()

                    if (forgetPasswordViewModel.isValidEmail) {
                        ToastMaker.showToast(
                            "You password reset code is sent to your registered email address.",
                            context
                        )
                        viewModelStore.clear() //Clearing viewModel to avoid data leak to subsequent visit to fragment
                        findNavController()
                            .navigate(R.id.action_forgetPassword_to_updatePassword)
                    } else {
                        ToastMaker.showToast("Email address is not registered.", context)
                    }

                }
            }
        }






        return fragmentView
    }
}