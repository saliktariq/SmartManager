package app.smartmanager.datalayer.typeconverter

import androidx.room.TypeConverter
import java.util.*

class DateTypeConverter {

    /*
    Created using following Google Android Developers API guide: https://developer.android.com/training/data-storage/room/referencing-data
     */

    @TypeConverter
    fun fromTimeStampToDate(timestamp: Long?): Date? {
        return timestamp?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun fromDateToTimeStamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

}