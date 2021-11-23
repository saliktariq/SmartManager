package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import app.smartmanager.datalayer.entity.Supplier

@Dao
interface SupplierDAO {

    @Insert(onConflict = IGNORE)
    suspend fun addSupplier(supplier: Supplier)

    @Query("SELECT * from supplier ORDER BY id ASC")
    fun readAllSupplierData(): LiveData<List<Supplier>>

    

}