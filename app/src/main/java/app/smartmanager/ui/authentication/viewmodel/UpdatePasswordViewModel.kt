package app.smartmanager.ui.authentication.viewmodel

import androidx.lifecycle.ViewModel
import app.smartmanager.helper.HelperFunctions
import app.smartmanager.helper.Mailer
import app.smartmanager.repository.SmartManagerRepo

class UpdatePasswordViewModel : ViewModel() {
/*
Following code is from my own work that I carried out
during university project making 'Archelon App'.
 */
//Variable to contain user email address entered in the Fragment
lateinit var emailAddress: String

    //Variable to contain authentication code entered in the Fragment
    var authCode: Long? = 0

    //Variable to contain new password entered in the Fragment
    lateinit var newPassword: String

    //Variable to contain repeat password entered in the Fragment
    lateinit var retypeNewPassword: String

    //Variable to check if queries has completed successfully
//    var isValidEmail: Boolean = false
    var isOperationCompleted: Boolean = false

    //Variables to hold data from the datasource
    var retrievedEmailAddress: String? = null
    var retrievedAuthCode: Long = 0
    var retrievedUserName: String? = null
    var retrievedFirstName: String? = null


    //Getting instance of Repository
    private val repository: SmartManagerRepo = SmartManagerRepo.get()


    //Function to check if password and retype password are equal
    fun passwordEqualsRetypePassword(): Boolean {
        return newPassword.equals(retypeNewPassword)
    }

    //Function to retrieve userdata
    suspend fun getUserData() {
        val data = repository.getUserDataByEmail(emailAddress)
        if (data != null) {
            retrievedAuthCode = data.authCode
            retrievedEmailAddress = data.email
            retrievedFirstName = data.firstName
        }
    }

    //Function to check if entered email is present in the dataset retrieved
    fun emailPresentInDataSource(): Boolean {
        return ((emailAddress.equals(retrievedEmailAddress) && (retrievedEmailAddress != null)))
    }

    //Function to check if entered authorization code is valid code in the dataset retrieved
    fun authCodePresentInDataSource(): Boolean {
        return (authCode == retrievedAuthCode)
    }

    //Function to update new authCode and password for a given email addres
    suspend fun updatePasswordAndAuthCode() {

        //Updating AuthCode for User dataset
        val newAuthCode = HelperFunctions.generateAuthCode(
            retrievedUserName,
            newPassword,
            emailAddress,
            retrievedFirstName
        )
        repository.updateAuthCode(newAuthCode, emailAddress)

        //Updating password
        repository.updatePassword(newPassword.hashCode().toLong(), emailAddress)

            //Sending email to user indicating that their password is reset and sending information about updated account details
        Mailer.sendMail(emailAddress,"SmartManager: Your new password","Dear $retrievedFirstName,\n" +
                "                         Your password is successfully updated.\n Please use the following login details to sign into the App: +\n" +
                "                        Associated email address: $emailAddress \n" +
                "                        New password: $newPassword \n")


        //Setting isOperationCompleted to true indicating that the password reset process has succeeded
        isOperationCompleted = true

    }





}