package app.smartmanager.helper

import android.text.TextUtils

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

        fun noNullMinLengthOne(string: String): Boolean {
            return (string.length > 0)
        }

        fun isFloat(number: String): Boolean {
            return when(number.toFloatOrNull())
            {
                null -> false
                else -> true
            }
        }
    }

}