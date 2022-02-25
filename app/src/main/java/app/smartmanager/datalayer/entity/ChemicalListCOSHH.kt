package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
@Parcelize
@Entity(tableName = "chemicalList")
data class ChemicalListCOSHH(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val purpose: String?,
    val concentration: String?,
    val notes: String? //any warnings etc
): Parcelable