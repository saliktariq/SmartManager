package app.smartmanager.ui.auth

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class LoginViewModel : ViewModel() {

    //Variables to hold data extracted from GoogleSignIn object
    private var displayName: String? = null
    private var firstName: String? = null
    private var lastName: String? = null
    private var emailAddress: String? = null
    private var googleID: String? = null
    private var photo: Uri? = null


    /*
    Function to assign data from received GoogleSignInAccount object
    to viewmodel variables
     */
    fun populateUserData(accountData: GoogleSignInAccount){

        if(accountData != null){
            displayName = accountData.displayName!!
            firstName = accountData.givenName!!
            lastName = accountData.familyName!!
            emailAddress = accountData.email!!
            googleID = accountData.id!!
            photo = accountData.photoUrl
        }

    }



}