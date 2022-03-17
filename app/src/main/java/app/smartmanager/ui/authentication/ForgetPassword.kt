package app.smartmanager.ui.authentication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.ui.authentication.viewmodel.ForgetPasswordViewModel

class ForgetPassword : Fragment() {

    private lateinit var forgetPasswordViewModel: ForgetPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.forget_password_fragment, container, false)
        forgetPasswordViewModel = ViewModelProvider(this).get(ForgetPasswordViewModel::class.java)






        return fragmentView
    }
}