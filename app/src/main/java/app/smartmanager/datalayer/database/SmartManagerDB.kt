package app.smartmanager.datalayer.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.smartmanager.datalayer.dataaccessobjects.ProbeDAO
import app.smartmanager.datalayer.entity.Probe

// Database definition of SQLite database utilising ROOM database library

@Database(entities = [Probe::class],version = 1, exportSchema = false)
abstract class SmartManagerDB: RoomDatabase(){
    abstract val probeDAO :ProbeDAO

    companion object{
        @Volatile
        private var INSTANCE: SmartManagerDB? = null

        fun getInstance(context: Context): SmartManagerDB {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SmartManagerDB::class.java,
                        "smartmanagerdb"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}