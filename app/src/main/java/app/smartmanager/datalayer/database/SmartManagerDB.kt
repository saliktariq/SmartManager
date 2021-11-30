package app.smartmanager.datalayer.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.smartmanager.datalayer.dataaccessobjects.*
import app.smartmanager.datalayer.entity.*
import app.smartmanager.datalayer.typeconverter.DateTypeConverter

// Database definition of SQLite database utilising ROOM database library

@Database(entities = [Probe::class, Supplier::class, Equipment::class, ControlChecks::class, CleaningTask::class, ChemicalListCOSHH::class, ProbeCalibrationRecord::class ],version = 3, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class SmartManagerDB: RoomDatabase(){
    abstract val probeDAO :ProbeDAO
    abstract val supplierDAO: SupplierDAO
    abstract val equipmentDAO: EquipmentDAO
    abstract val controlChecksDAO: ControlChecksDAO
    abstract val cleaningTaskDAO: CleaningTaskDAO
    abstract val chemicalListCOSHHDAO: ChemicalListCOSHHDAO
    abstract val probeCalibrationRecordDAO: ProbeCalibrationRecordDAO

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