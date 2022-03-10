package app.smartmanager.datalayer.entity



import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import java.util.*

@Parcelize
@Entity(tableName = "cooking_record")
data class Cooking(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val cooked_product_name: String,
    val quantity: Int,
    val timestamp: Date
): Parcelable