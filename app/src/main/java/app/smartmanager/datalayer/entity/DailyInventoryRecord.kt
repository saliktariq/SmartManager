package app.smartmanager.datalayer.entity



import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import java.util.*

@Parcelize
@Entity(tableName = "daily_inventory_record")
data class DailyInventoryRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val product_name: String,
    val quantity: Int,
    val timestamp: Date
): Parcelable