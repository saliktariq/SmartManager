package app.smartmanager.helper

import android.text.TextUtils
import java.util.*

class HelperFunctions {


    companion object{
        /*
Function to check if not-null fields are not empty
*/
        fun checkInputData(dataToCheck: String?): Boolean{
            return !(TextUtils.isEmpty(dataToCheck))
        }


        //https://www.techiedelight.com/determine-string-is-number-kotlin/
        fun isNumber(number: String): Boolean {
            return when(number.toIntOrNull())
            {
                null -> false
                else -> true
            }
        }

        fun noNullMinLengthOne(string: String?): Boolean {
            if (string.isNullOrBlank()){
                return false
            } else {
                return (string.length>0)
            }
        }

        fun isFloat(number: String): Boolean {
            return when(number.toFloatOrNull())
            {
                null -> false
                else -> true
            }
        }

        fun getOldDate(tMinusXDays: Int): Date {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -tMinusXDays)

            return calendar.time
        }
        /*
        generateAuthCode is my own work that I wrote while completing a module project at Birkbeck
         */
        fun generateAuthCode(
            username: String?,
            pwd: String,
            email: String,
            firstName: String?
        ): Long {

            val userHash = username.hashCode()
            val pwdHash = pwd.hashCode()
            val emailHash = email.hashCode()
            val firstNameHash = firstName.hashCode()
            val currentTime =
                System.currentTimeMillis() //Adding a random unique variable to the hash mix

            var hashCode = userHash + pwdHash + emailHash + firstNameHash + currentTime
            val stringLength = hashCode.toString().length
            val targetLength: Int

            //Managing the length of hashCode
            //Update this variable to control the length of auth code.
            val codeLength = 13
            targetLength = if (stringLength < codeLength) {
                stringLength
            } else {
                codeLength
            }

            //Setting the desired length of the hashCode, and returning it as a Long
            hashCode = (hashCode.toString().substring(0, targetLength)).toLong()
            return hashCode
        }
    }

}