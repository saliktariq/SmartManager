package app.smartmanager.ui.authentication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.solver.widgets.Helper
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.smartmanager.R
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.authentication.viewmodel.RegisterNewAccountViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class RegisterNewAccount : Fragment() {

    private lateinit var registerNewAccountViewModel: RegisterNewAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.register_new_account_fragment, container, false)
        registerNewAccountViewModel = ViewModelProvider(this).get(RegisterNewAccountViewModel::class.java)
        val firstName: TextInputEditText = fragmentView.findViewById(R.id.firstName)
        val emailAddress: TextInputEditText = fragmentView.findViewById(R.id.emailAddress)
        val username: TextInputEditText = fragmentView.findViewById(R.id.username)
        val password: TextInputEditText = fragmentView.findViewById(R.id.password)
        val buttonRegisterNewAccount: AppCompatButton = fragmentView.findViewById(R.id.buttonRegisterNewAccount)
        val btnSignIn: AppCompatTextView = fragmentView.findViewById(R.id.btnSignIn)
        val btnForgetPassword: AppCompatTextView = fragmentView.findViewById(R.id.btnForgetPassword)

        btnForgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_registerNewAccount_to_forgetPassword)
        }

        btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_global_loginFragment)
        }

        buttonRegisterNewAccount.setOnClickListener {
            //Assigning user entered data to view model variables
            registerNewAccountViewModel.firstName = firstName.text.toString()
            registerNewAccountViewModel.email = emailAddress.text.toString()
            registerNewAccountViewModel.username = username.text.toString()
            registerNewAccountViewModel.pwd = password.text.toString()

            //Generating new authentication code
            registerNewAccountViewModel.authCode = HelperFunctions.generateAuthCode(
                registerNewAccountViewModel.username,
                registerNewAccountViewModel.pwd,
                registerNewAccountViewModel.email,
                registerNewAccountViewModel.firstName
            )

            //Starting coroutine with application lifecycle scope
            viewLifecycleOwner.lifecycleScope.launch {
                //Checking if the registration data entered is not already in the system
                val fetchData = viewLifecycleOwner.lifecycleScope.launch {
                    registerNewAccountViewModel.getUserData(registerNewAccountViewModel.username)
                }
                //Query database to see if the dataset (username or email address) already exists
                //Waiting for the coroutine to complete query
                fetchData.join()


                //Checking if email already exist in the system
                if (registerNewAccountViewModel.emailAlreadyExists) {
                    ToastMaker.showToastWithGravity(
                        "Email address already exist.",
                        context,
                        Gravity.TOP
                    )
                }
                //Checking if email field is empty or inval
                else if (registerNewAccountViewModel.email.length < 3) {
                    val toastHandler = Handler()
                    toastHandler.postDelayed({
                        ToastMaker.showToastWithGravity(
                            "Enter a valid email address",
                            context,
                            Gravity.TOP
                        )
                    }, 2000)
                }

                //Checking if username field is empty or invalid
                else if (registerNewAccountViewModel.username.length < 5) {
                    val toastHandler = Handler()
                    toastHandler.postDelayed({
                        ToastMaker.showToastWithGravity(
                            "Minimum username length is 5 characters",
                            context,
                            Gravity.TOP
                        )
                    }, 2000)
                } else if (registerNewAccountViewModel.userAlreadyExists) {
                    /*Using handler to display second toast right after the first error toast
                    to avoid second toast override first toast message.
                     */
                    val toastHandler = Handler()
                    toastHandler.postDelayed({
                        ToastMaker.showToastWithGravity(
                            "Username already exist.",
                            context,
                            Gravity.TOP
                        )
                    }, 2000) //Adding delay to avoid toasts overlapping in case of multiple errors
                }

                //Checking if password field is 5 characters or more
                else if (registerNewAccountViewModel.pwd.length < 5) {
                    val toastHandler = Handler()
                    toastHandler.postDelayed({
                        ToastMaker.showToastWithGravity(
                            "Minimum password length is 5 characters",
                            context,
                            Gravity.BOTTOM
                        )
                    }, 2000) //Adding delay to avoid toasts overlapping in case of multiple errors
                } else {
                    val signup = viewLifecycleOwner.lifecycleScope.launch {
                        registerNewAccountViewModel.signUpUser()
                    }
                    //Waiting for signup query to complete
                    signup.join()
                    ToastMaker.showToastWithGravity(
                        "Success! User account created",
                        context,
                        Gravity.TOP
                    )

                    viewModelStore.clear() //Clearing viewModel
                   findNavController().navigate(R.id.action_global_loginFragment)
                }
                registerNewAccountViewModel.emailAlreadyExists = false
                registerNewAccountViewModel.userAlreadyExists = false
            }
        }
        return fragmentView

    }
}