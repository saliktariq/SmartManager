package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import java.util.*

@Parcelize
@Entity(tableName = "food_waste_record")
data class FoodWasteRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val cooked_product_name: String,
    val waste_quantity: Int,
    val timestamp: Date
): Parcelable