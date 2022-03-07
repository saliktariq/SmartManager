package app.smartmanager.datalayer.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cooked_product_item")
data class CookedProductItem (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val quantityPerCookingBatch: Int?
) : Parcelable
