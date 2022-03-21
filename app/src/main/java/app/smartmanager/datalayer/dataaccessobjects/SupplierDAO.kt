package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.Supplier

@Dao
interface SupplierDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addSupplier(supplier: Supplier)

    @Query("SELECT * from supplier ORDER BY id DESC")
    fun readAllSupplierData(): LiveData<List<Supplier>>

    @Update
    fun updateSupplier(supplier: Supplier)

    @Delete
    fun deleteSupplier(supplier: Supplier)

    //Query to retrieve all supplier names to be used by other classes
    @Query("Select name from supplier ORDER BY id DESC")
    fun readSupplierName(): LiveData<List<String>>

    @Query("Select name from supplier ORDER BY id DESC")
    fun readSupplierNameForUnitTests(): List<String>
}
