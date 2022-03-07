package app.smartmanager.datalayer.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "inventory_item")
data class InventoryItem (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val supplier: String?,
    val quantityPerUnit: Int?
) : Parcelable
