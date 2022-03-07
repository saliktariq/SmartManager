package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.CookedProductItem
import app.smartmanager.datalayer.entity.InventoryItem
import app.smartmanager.ui.setup.CookedProductItemFragmentDirections
import app.smartmanager.ui.setup.InventoryItemFragmentDirections

class InventoryItemAdapter :
    RecyclerView.Adapter<InventoryItemAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val inventoryName = itemView.findViewById<TextView>(R.id.inventoryName)
        val inventorySupplier = itemView.findViewById<TextView>(R.id.inventorySupplier)
        val inventoryQuantityPerUnit =
            itemView.findViewById<TextView>(R.id.inventoryQuantityPerUnit)

        //Implementing onClickListener to access a single record
        val inventoryItemSingleRecord =
            itemView.findViewById<LinearLayout>(R.id.inventoryItemSingleRecord)

        fun bind(data: InventoryItem) {
            inventoryName.text = data.name
            inventorySupplier.text = data.supplier
            inventoryQuantityPerUnit.text = data.quantityPerUnit.toString()

            //Implementing onClickListener to navigate to relevant update fragment
            inventoryItemSingleRecord.setOnClickListener {
                val action =
                    InventoryItemFragmentDirections.actionInventoryItemFragmentToUpdateInventoryItemFragment(
                        data
                    )
                itemView.findNavController().navigate(action)

            }


        }

    }

    // List to contain InventoryItem objects

    private var inventoryItemRecordList = emptyList<InventoryItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InventoryItemAdapter.MyViewHolder {

        //Returning InventoryItem list itemview
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.inventory_item_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: InventoryItemAdapter.MyViewHolder, position: Int
    ) {
        //Setting current record
        val currentRecord = inventoryItemRecordList[position]
        holder.bind(inventoryItemRecordList[position])
    }

    fun setDataSet(inventoryItem: List<InventoryItem>) {

        this.inventoryItemRecordList = inventoryItem

        //Notifying UI of data update
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return inventoryItemRecordList.size
    }
}