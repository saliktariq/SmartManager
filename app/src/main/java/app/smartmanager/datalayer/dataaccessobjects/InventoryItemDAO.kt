package app.smartmanager.datalayer.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import app.smartmanager.datalayer.entity.InventoryItem

@Dao
interface InventoryItemDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addInventoryItemRecord(record: InventoryItem)

    @Query("Select * from inventory_item ORDER BY id DESC")
    fun readAllInventoryItemData(): LiveData<List<InventoryItem>>

    @Update
    fun updateInventoryItemRecord(record: InventoryItem)

    @Delete
    fun deleteInventoryItemRecord(record: InventoryItem)

    //Query to retrieve only the list of names of inventory items to be used by other classes
    @Query("Select name from inventory_item ORDER BY id DESC")
    fun readInventoryItemName(): LiveData<List<String>>

}