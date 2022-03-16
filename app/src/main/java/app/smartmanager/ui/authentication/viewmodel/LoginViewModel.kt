package app.smartmanager.ui.authentication.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import app.smartmanager.repository.SmartManagerRepo
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

    /*
Following code is from my own work that I carried out
during university project making 'Archelon App'.
 */

    //Variables to hold UI data
    lateinit var uid: String
    lateinit var pwd: String

    //Flag set to true if credentials match
    var authenticated: Boolean = false

    //Initialising repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    /**
     * Function login to authenticate user to access the application
     * If credentials are correct, true is returned and authenticated
     * variable is set to true
     * @param uid of type String containing username
     * @param pwd of type String containing password
     *
     */

    suspend fun login(uid: String, pwd: String) {
        //Length check to avoid unnecessary I/O call to database
        if (uid.length >= 5 && pwd.length >= 5) {
            val userData = repository.getUserData(uid)
            //If query receives nothing userData is still null
            if (userData == null) {
                authenticated = false
            } else {
                //Comparing password hash with saved hash for password
                if (userData.pwd.equals(pwd.hashCode().toLong())) {
                    authenticated = true
                }
            }


        }
    }



}