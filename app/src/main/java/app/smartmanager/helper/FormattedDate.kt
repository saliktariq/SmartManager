package app.smartmanager.helper

import android.widget.DatePicker
import java.util.*


class FormattedDate {
    companion object{
        fun createDateObjectFromDatePicker(datePicker: DatePicker): Date {
            val extractedYear = datePicker.year
            val extractedMonth = datePicker.month
            val extractedDay = datePicker.dayOfMonth
//            val calendarObject = Calendar.getInstance()

            var dateObject: Date = Date()
            dateObject.setYear(extractedYear)
            dateObject.setMonth(extractedMonth)
            dateObject.setDate(extractedDay)
            return dateObject
//            return calendarObject.time
        }
    }
}