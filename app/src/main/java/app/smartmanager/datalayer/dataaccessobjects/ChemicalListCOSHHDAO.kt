package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import app.smartmanager.datalayer.entity.ChemicalListCOSHH
import app.smartmanager.datalayer.entity.CleaningTask


@Dao
interface ChemicalListCOSHHDAO {

    @Insert(onConflict = IGNORE)
    fun addChemicalListCOSHH(chemicalListCOSHH: ChemicalListCOSHH)

    @Query("SELECT * from chemicalList ORDER BY id ASC")
    fun readAllChemicalListCOSHHData(): LiveData<List<ChemicalListCOSHH>>

    @Update
    fun updateChemicalListCOSHH(chemicalListCOSHH: ChemicalListCOSHH)

    @Delete
    fun deleteChemicalListCOSHH(chemicalListCOSHH: ChemicalListCOSHH)


    @Query("SELECT * from chemicalList ORDER BY id ASC")
    fun readDataQueryForUnitTest(): List<ChemicalListCOSHH>
}