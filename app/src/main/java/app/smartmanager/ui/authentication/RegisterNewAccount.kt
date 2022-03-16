package app.smartmanager.ui.authentication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.smartmanager.R
import app.smartmanager.ui.authentication.viewmodel.RegisterNewAccountViewModel

class RegisterNewAccount : Fragment() {

    companion object {
        fun newInstance() = RegisterNewAccount()
    }

    private lateinit var viewModel: RegisterNewAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_new_account_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterNewAccountViewModel::class.java)
        // TODO: Use the ViewModel
    }

}