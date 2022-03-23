package app.smartmanager.helper

import android.widget.DatePicker
import java.util.*


class FormattedDate {
    companion object {

        /*
        Function to create date object from datepicker object
        Function not used in the project
         */
        fun createDateObjectFromDatePicker(datePicker: DatePicker): Date {
            val extractedYear = datePicker.year
            val extractedMonth = datePicker.month
            val extractedDay = datePicker.dayOfMonth

            val dateObject: Date = Date()
            dateObject.setYear(extractedYear)
            dateObject.setMonth(extractedMonth)
            dateObject.setDate(extractedDay)
            return dateObject
        }

    }
}