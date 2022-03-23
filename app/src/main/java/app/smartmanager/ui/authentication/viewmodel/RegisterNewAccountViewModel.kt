package app.smartmanager.ui.authentication.viewmodel

import android.os.Build
import androidx.lifecycle.ViewModel
import app.smartmanager.helper.Mailer
import app.smartmanager.repository.SmartManagerRepo

class RegisterNewAccountViewModel : ViewModel() {
/*
Following code is from my own work that I carried out
during university project making 'Archelon App'.
 */

    //Local variables to hold form data
    var username: String = ""
    var pwd: String = ""
    var email: String = ""
    var firstName: String = ""
    var authCode: Long = 0L //Used to reset password

    //Variables to hold error flags
    var userAlreadyExists: Boolean = false
    var emailAlreadyExists: Boolean = false

    //Variables to hold data from the datasource
    var retrievedEmailAddress: String? = null
    var retrievedUserName: String? = null


    //Getting instance of Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    //Function to retrieve user data based on username
    suspend fun getUserData(username: String) {
        if (username != null && username.length >= 5) {
            val data = repository.getUserData(username)
            if (data != null) {
                retrievedUserName = data.username
                retrievedEmailAddress = data.email
                //checking email length greater than 3 as it is the theoretical smallest email size
                if ((retrievedEmailAddress.equals(email)) && retrievedEmailAddress!!.length > 3) {
                    emailAlreadyExists = true
                }

                //Checking retrieved username length to avoid null entries
                if ((retrievedUserName.equals(username)) && retrievedUserName!!.length > 3) {
                    userAlreadyExists = true
                }
            }
        }


    }

    //Insert data into database using repository
    suspend fun signUpUser() {

        repository.insertAuthenticationData(
            0,
            username,
            pwd.hashCode().toLong(),
            email,
            firstName,
            authCode
        )

        //Send email to new user with account details
        Mailer.SendMail(
            email, "Your new SmartManager account", "Dear $firstName,\n" +
                    "                        Your account is successfully registered.\n\n Following are the details of your new account:\n" +
                    "                        Username: $username \n" +
                    "                       Password: $pwd"
        )


    }


}