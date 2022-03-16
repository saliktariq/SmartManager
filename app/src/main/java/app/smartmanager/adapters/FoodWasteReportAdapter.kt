package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.FoodWasteRecord



class FoodWasteReportAdapter() : RecyclerView.Adapter<FoodWasteReportAdapter.MyViewHolder>() {

    var foodWasteRecord = emptyList<FoodWasteRecord>()

    fun setFoodWasteRecordData(data: List<FoodWasteRecord>){
        this.foodWasteRecord = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodWasteReportAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.food_waste_report_list_item,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(foodWasteRecord[position])
    }

    override fun getItemCount(): Int {
        return foodWasteRecord.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cookedProductName: TextView = view.findViewById<TextView>(R.id.cookedProductName)
        val wasteQuantity: TextView = view.findViewById<TextView>(R.id.wasteQuantity)
        val date: TextView = view.findViewById<TextView>(R.id.date)
        fun bind(data: FoodWasteRecord) {
            cookedProductName.text = data.cooked_product_name
            wasteQuantity.text = data.waste_quantity.toString()
            date.text = data.timestamp.toString()
        }
    }
}