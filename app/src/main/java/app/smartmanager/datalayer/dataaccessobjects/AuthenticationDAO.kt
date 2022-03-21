package app.smartmanager.datalayer.dataaccessobjects

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import app.smartmanager.datalayer.entity.Authentication

/*
This code is from my own work that I carried out
during university project making 'Archelon App'.
 */
@Dao
interface AuthenticationDAO {

    //Query to register a new user to system
    @Insert
    fun insertAuthenticationData(userData: Authentication)

    //Query to update user password based on email address
    @Query("UPDATE authentication SET pwd = :arg0 WHERE email = :arg1")
//    fun updatePassword(newPassword: String, email: String)
    fun updatePassword(arg0: Long, arg1: String)

    //Query to update user authCode based on email address
    @Query("UPDATE authentication SET authCode = :arg0 WHERE email = :arg1")
    fun updateAuthCode(arg0: Long, arg1: String)

    // Query to retrieve all data based on username
    @Query("Select * from authentication WHERE username = :arg0 ORDER BY username DESC LIMIT 1")
    fun getUserData(arg0: String): Authentication?

    // Query to retrieve password based on username
    @Query("Select pwd from authentication WHERE username = :arg0")
//    fun getPassword(key: String): String?
    fun getPassword(arg0: String): Long?

    // Query to retrieve authCode based on given email
    @Query("Select authCode from authentication WHERE email = :arg0 ORDER BY email DESC LIMIT 1")
    fun getAuthCodeBasedOnEmail(arg0: String): Long

    // Query to retrieve all data based on email address
    @Query("Select * from authentication WHERE email = :arg0 ORDER BY email DESC LIMIT 1")
    fun getUserDataByEmail(arg0: String?): Authentication?

    //Tansaction query to update the password and update new authCode for the user based on email
    @Transaction
    fun updatePasswordAndAuthCode(email: String, password: Long, authCode: Long){
        updatePassword(password, email)
        updateAuthCode(authCode, email)
    }





}