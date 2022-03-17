package app.smartmanager.ui.authentication.viewmodel

import android.os.Build
import androidx.lifecycle.ViewModel
import app.smartmanager.datalayer.entity.Authentication
import app.smartmanager.helper.Mailer
import app.smartmanager.repository.SmartManagerRepo

class ForgetPasswordViewModel : ViewModel() {

//Variable to contain user email address
lateinit var emailAddress: String
    var isValidEmail: Boolean = false

    //Getting instance of Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()

    //Getting dataset (Authentication) based on email address
    suspend fun getUserData() {
        repository.getUserDataByEmail(emailAddress)
    }

    /*
Following code is from my own work that I carried out
during university project making 'Archelon App'.
 */

    //Send Password reset code to email
    suspend fun sendAuthCode() {
        val userData = repository.getUserDataByEmail(emailAddress)
        //Checking if requested data is actually fetched
        if (userData != null) {
            //Retrieving authCode from the fetched dataset
            val authCode = userData.authCode
                //Send email to new user with account details
            Mailer.SendMail(emailAddress,"Your new password reset code","Dear ${userData.firstName},\n\n" +
                    " This is your one time password reset code.\n\n Please use the following code to reset your password.:\n\n\n" +
                    "Associated email address: $emailAddress\n\n" +
                    "Password reset code: $authCode \n")



            //Setting isValidEmail to true indicating that the email entered by user was valid
            isValidEmail = true

        }
    }

    override fun onCleared() {
        super.onCleared()
        //Making sure following variables are reset to avoid subsequent unwanted processing while
        //fragment is not destroyed
        emailAddress = ""
        isValidEmail = false

    }


}