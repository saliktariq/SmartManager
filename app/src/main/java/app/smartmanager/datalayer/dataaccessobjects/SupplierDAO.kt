package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.Supplier

@Dao
interface SupplierDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addSupplier(supplier: Supplier)

    @Query("SELECT * from supplier ORDER BY id ASC")
    fun readAllSupplierData(): LiveData<List<Supplier>>

    @Update
    fun updateSupplier(supplier: Supplier)
}