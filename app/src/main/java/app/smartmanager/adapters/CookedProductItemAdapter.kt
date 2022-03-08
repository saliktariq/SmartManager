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
import app.smartmanager.ui.setup.CookedProductItemFragmentDirections
import java.text.FieldPosition

class CookedProductItemAdapter :
RecyclerView.Adapter<CookedProductItemAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cookedProductName = itemView.findViewById<TextView>(R.id.cookedProductName)
        val quantityPerCookingBatch = itemView.findViewById<TextView>(R.id.quantityPerCookingBatch)
        val relatedProduct = itemView.findViewById<TextView>(R.id.relatedProduct)

        //Implementing onClickListener to access a single record
        val cookedProductItemSingleRecord =
            itemView.findViewById<LinearLayout>(R.id.cookedProductItemSingleRecord)

        fun bind(data: CookedProductItem){
            cookedProductName.text = data.name
            quantityPerCookingBatch.text = data.quantityPerCookingBatch.toString()
            relatedProduct.text = data.relatedInventoryItem

            //Implementing onClickListener to navigate to relevant update fragment
            cookedProductItemSingleRecord.setOnClickListener {
                val action = CookedProductItemFragmentDirections.actionCookedProductItemFragmentToUpdateCookedProductItemFragment(data)
                itemView.findNavController().navigate(action)

            }


        }

    }

    // List to contain CookingProductItem objects

    private var cookedProductItemRecordList = emptyList<CookedProductItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookedProductItemAdapter.MyViewHolder {

        //Returning CookedProductItem list itemview
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cooked_product_item_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: CookedProductItemAdapter.MyViewHolder, position: Int
    ){
        //Setting current record
        val currentRecord = cookedProductItemRecordList[position]
        holder.bind(cookedProductItemRecordList[position])
    }

    fun setDataSet(cookedProductItem: List<CookedProductItem>){
        this.cookedProductItemRecordList = cookedProductItem

        //Notifying UI of data update
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cookedProductItemRecordList.size
    }
}