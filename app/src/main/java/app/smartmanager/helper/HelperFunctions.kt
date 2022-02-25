package app.smartmanager.helper

import android.text.TextUtils

class HelperFunctions {


    companion object{
        /*
Function to check if not-null fields are not empty
*/
        fun checkInputData(dataToCheck: String): Boolean{
            return !(TextUtils.isEmpty(dataToCheck))
        }
    }

}