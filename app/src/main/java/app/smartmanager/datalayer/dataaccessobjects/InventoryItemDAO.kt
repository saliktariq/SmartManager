package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.InventoryItem

@Dao
interface InventoryItemDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addInventoryItemRecord(record: InventoryItem)

    @Query("Select * from cooked_product_item ORDER BY id ASC")
    fun readAllInventoryItemData(): LiveData<List<InventoryItem>>

    @Update
    fun updateInventoryItemRecord(record: InventoryItem)

    @Delete
    fun deleteInventoryItemRecord(record: InventoryItem)

}