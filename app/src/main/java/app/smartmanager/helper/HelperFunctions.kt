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
    }

}