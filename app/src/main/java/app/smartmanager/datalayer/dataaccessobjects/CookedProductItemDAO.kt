package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.CookedProductItem

@Dao
interface CookedProductItemDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCookedProductItemRecord(record: CookedProductItem)

    @Query("Select * from cooked_product_item ORDER BY id DESC")
    fun readAllCookedProductItemData(): LiveData<List<CookedProductItem>>

    @Update
    fun updateCookedProductItemRecord(record: CookedProductItem)

    @Delete
    fun deleteCookedProductItemRecord(record: CookedProductItem)

    @Query("Select name from cooked_product_item ORDER BY id DESC")
    fun listAllCookedProductItem(): LiveData<List<String>>

    @Query("Select name from cooked_product_item ORDER BY id DESC")
    fun cookedProductItemForUnitTest(): List<String>

    @Query("Select * from cooked_product_item ORDER BY id DESC")
    fun readAllCookedProductItemDataForTesting(): List<CookedProductItem>

}