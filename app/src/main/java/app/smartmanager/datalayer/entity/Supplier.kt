package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supplier")
data class Supplier(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val email: String?,
    val phone: String?,
    val address: String?
)