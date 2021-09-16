package app.smartmanager.ui.auth

import android.R.attr
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

class LoginFragment : Fragment() {

    private var binding: LoginFragmentBinding? = null
    private val viewBinding get() = binding!!
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var account: GoogleSignInAccount


    companion object {
        // A constant to produce result on onActivityResult
        const val GOOGLE_CONSTANT = 2021
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        val viewModelFactory = LoginViewModelFactory()
        val loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        viewBinding.signInButton.setSize(SignInButton.SIZE_WIDE)
        googleSignInClient = GoogleSignIn.getClient(requireContext(), getGSO())

        viewBinding.signInButton.setOnClickListener {
           signIn()
        }


        return viewBinding.root
    }


    private fun getGSO(): GoogleSignInOptions {
        return  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
    }

    private fun signIn(){
        val signInIntent = googleSignInClient.signInIntent
//        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(signInIntent)
        startActivityForResult(signInIntent, GOOGLE_CONSTANT);
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            account = completedTask.getResult(ApiException::class.java)
            val text = "Successful signin"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, text, duration)
            toast.show()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == GOOGLE_CONSTANT) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(context)
        if(account == null){
            val text = "Account is null"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Destroying the view binding
        binding = null
    }

}