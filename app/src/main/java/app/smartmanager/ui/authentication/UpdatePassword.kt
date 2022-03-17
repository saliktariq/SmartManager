package app.smartmanager.ui.authentication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.smartmanager.R
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.authentication.viewmodel.UpdatePasswordViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class UpdatePassword : Fragment() {

    companion object {
        fun newInstance() = UpdatePassword()
    }

    private lateinit var updatePasswordViewModel: UpdatePasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.update_password_fragment, container, false)
        updatePasswordViewModel = ViewModelProvider(this).get(UpdatePasswordViewModel::class.java)
        val username: TextInputEditText = fragmentView.findViewById(R.id.username)
        val authenticationCode: TextInputEditText = fragmentView.findViewById(R.id.authorizationCode)
        val password: TextInputEditText = fragmentView.findViewById(R.id.password)
        val retypePassword: TextInputEditText = fragmentView.findViewById(R.id.retypePassword)
        val resetPasswordButton: AppCompatButton = fragmentView.findViewById(R.id.buttonResetPassword)
        val signUpTextView: AppCompatTextView = fragmentView.findViewById(R.id.btnSignUp)
        val requestNewAuthCode: AppCompatTextView = fragmentView.findViewById(R.id.btnForgetPassword)

        requestNewAuthCode.setOnClickListener {
            findNavController().navigate(R.id.action_updatePassword_to_forgetPassword)
        }

        signUpTextView.setOnClickListener {
            findNavController().navigate(R.id.action_updatePassword_to_registerNewAccount)
        }

        resetPasswordButton.setOnClickListener {

            /*
Following code is an adaptation of my own work that I carried out
during university project making 'Archelon App'.
*/
            //Setting the viewModel variables
            updatePasswordViewModel.emailAddress = username.text.toString()
            updatePasswordViewModel.authCode = (authenticationCode.text.toString()).toLongOrNull()
            updatePasswordViewModel.newPassword = password.text.toString()
            updatePasswordViewModel.retypeNewPassword = retypePassword.text.toString()


            //Checking if matching password and retype password are entered ahead of running a coroutine
            if (!(updatePasswordViewModel.passwordEqualsRetypePassword())) {
                ToastMaker.showToast("Password and retype Password must be equal", context)
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
                    //Assigning getUserData to variable fetchData
                    val fetchData = viewLifecycleOwner.lifecycleScope.launch {
                        updatePasswordViewModel.getUserData()
                    }
                    //Fetch data and update variable isValidEmail before checking this variable in condition
                    fetchData.join()
                    if (!updatePasswordViewModel.emailPresentInDataSource()) {
                        ToastMaker.showToastWithGravity(
                            "Email address is incorrect.",
                            context,
                            Gravity.BOTTOM
                        )
                    }


                    if (!(updatePasswordViewModel.authCodePresentInDataSource())) {
                        /*Using handler to display second toast right after the email error toast
                        to avoid second toast override first toast message
                         */
                        val toastHandler = Handler()
                        toastHandler.postDelayed(
                            {
                                ToastMaker.showToastWithGravity(
                                    "Authorization code is incorrect.",
                                    context,
                                    Gravity.BOTTOM
                                )
                            },
                            2000
                        ) //Delay is added to avoid toasts overlapping in case of multiple errors
                    } else {
                        val changePassword = viewLifecycleOwner.lifecycleScope.launch {
                            updatePasswordViewModel.updatePasswordAndAuthCode()
                        }
                        changePassword.join()
                        if (updatePasswordViewModel.isOperationCompleted) {
                            ToastMaker.showToastWithGravity(
                                "Your password is reset successfully!",
                                context, Gravity.TOP
                            )
                            findNavController()
                                .navigate(R.id.action_updatePassword_to_homeScreen)
                        } else {
                            ToastMaker.showToastWithGravity(
                                "Error! Operation unsuccessful.",
                                context, Gravity.TOP
                            )
                        }

                    }
                }


            }

        }



        return fragmentView
    }



}