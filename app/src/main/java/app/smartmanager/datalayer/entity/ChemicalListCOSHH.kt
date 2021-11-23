package app.smartmanager.datalayer.entity

import androidx.room.Entity

@Entity(tableName = "chemicalList")
data class ChemicalListCOSHH(
    val id: Long,
    val name: String,
    val purpose: String?,
    val concentration: String?,
    val notes: String? //any warnings etc
)