package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import app.smartmanager.datalayer.entity.ChemicalListCOSHH


@Dao
interface ChemicalListCOSHHDAO {

    @Insert(onConflict = IGNORE)
    fun addChemicalListCOSHH(chemicalListCOSHH: ChemicalListCOSHH)

    @Query("SELECT * from chemicalList ORDER BY id ASC")
    fun readAllChemicalListCOSHHData(): LiveData<List<ChemicalListCOSHH>>


}