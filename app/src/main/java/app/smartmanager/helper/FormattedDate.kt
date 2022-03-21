package app.smartmanager.helper

import android.widget.DatePicker
import java.util.*


class FormattedDate {
    companion object{
        fun createDateObjectFromDatePicker(datePicker: DatePicker): Date? {
            val extractedYear = datePicker.year
            val extractedMonth = datePicker.month
            val extractedDay = datePicker.dayOfMonth
            val calendarObject = Calendar.getInstance()
            calendarObject.set(extractedYear, extractedMonth,extractedDay )
            return calendarObject.time
        }
    }
}