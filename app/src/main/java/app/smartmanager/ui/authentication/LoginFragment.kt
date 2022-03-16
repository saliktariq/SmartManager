package app.smartmanager.ui.authentication

import app.smartmanager.ui.authentication.viewmodel.LoginViewModel
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import app.smartmanager.databinding.LoginFragmentBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import android.content.ContentValues.TAG
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import android.content.Intent
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import app.smartmanager.R
import app.smartmanager.helper.ToastMaker
import app.smartmanager.ui.authentication.viewmodel.LoginViewModelFactory

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

/*
Kindly note: Google Sign-In integration learnt at: https://developers.google.com/identity/sign-in/android/start-integrating
Parts of google integration code is adapted from the above guide from Google
 */

class LoginFragment : Fragment() {

    private var binding: LoginFragmentBinding? = null
    private val viewBinding get() = binding!!

    //Variable to hold GoogleSignInClient object
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var account: GoogleSignInAccount

    lateinit var loginViewModel: LoginViewModel

    companion object {
        // A constant to produce result on onActivityResult
        const val GOOGLE_CONSTANT = 2021
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val usernameField: TextInputEditText = viewBinding.username
        val passwordField: TextInputEditText = viewBinding.password
        val loginButton: AppCompatButton = viewBinding.buttonLogin
        val signUpButton: AppCompatTextView = viewBinding.btnSignUp
        val forgetPasswordButton: AppCompatTextView = viewBinding.btnForgetPassword

        signUpButton.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerNewAccount)
        }

        forgetPasswordButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgetPassword)
        }


        viewBinding.signInButton.setSize(SignInButton.SIZE_WIDE)

        // Building GoogleSignInClient object based on options specified in getGSO() function
        googleSignInClient = GoogleSignIn.getClient(requireContext(), getGSO())


        // Attaching onClickListener to signInButton
        viewBinding.signInButton.setOnClickListener {
            //onclickListener triggers signIn() method
           signIn()
        }

        //Login button onClickListener to process login requests
        loginButton.setOnClickListener {
            loginViewModel.uid = usernameField.text.toString()
            loginViewModel.pwd = passwordField.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                val fetchData = viewLifecycleOwner.lifecycleScope.launch {
                    loginViewModel.login(loginViewModel.uid, loginViewModel.pwd)
                }
                //Fetch login data
                fetchData.join()
                if (loginViewModel.authenticated) {
                    viewModelStore.clear() //Clearing viewModel
                    viewBinding.root.findNavController()
                        .navigate(LoginFragmentDirections.actionLoginFragmentToHomeScreen())
                } else {
                    ToastMaker.showToast("Invalid credentials!", context)
                    //Resetting the variables so they don't hold bad data (Strings)
                    loginViewModel.uid = ""
                    loginViewModel.pwd = ""
                }
            }


        }


        return viewBinding.root
    }

    /*
    Method/Function to return GoogleSignInOptions object with basic profile and email address
     */
    private fun getGSO(): GoogleSignInOptions {
        return  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
    }

    private fun signIn(){

        val signInIntent = googleSignInClient?.signInIntent
        // Starting Google SignIn Intent that asks user to choose a Google account to sign in
        startActivityForResult(signInIntent, GOOGLE_CONSTANT);
    }


    /*
    Function to get GoogleSiginInAccount object for the signed in user
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        /* Call to super is required because fragment is a child of Activity and
        by default onActivityResult function returns the result to Activity function not fragment function
         */
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == GOOGLE_CONSTANT) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.

            lateinit var task: Task<GoogleSignInAccount>
            task = GoogleSignIn.getSignedInAccountFromIntent(data)
            //User data is processed by handleSignInResult() function
            handleSignInResult(task)
        }
    }

    // Function to process data from GoogleSignIn object
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {

        // Variables to hold LoginViewModel reference and LoginViewModelFactory
        val viewModelFactory = LoginViewModelFactory()
        val loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        try {
            account = completedTask.getResult(ApiException::class.java)

            /* Assigning the retrieved user data to LoginViewModel
            as fragment should not be used to hold process persistent data due
            to its short lifecycle
             */


            loginViewModel.populateUserData(account)

            /* Disconnecting Google client as authentication is compeleted and the limited requirement
               of google oAuth service in the application doesn't require further interaction with
               google services */

            googleSignInClient.signOut()
                .addOnCompleteListener(requireActivity(), OnCompleteListener<Void?> {
                    // ...
                })

            // Navigating to HomeScreen after processing data
            viewBinding.root.findNavController()
                .navigate(LoginFragmentDirections.actionGlobalHomeScreen())

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            val text = "Failed signin"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Destroying the view binding
        binding = null
    }

}