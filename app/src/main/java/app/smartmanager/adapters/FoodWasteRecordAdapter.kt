package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.FoodWasteRecord


// https://www.youtube.com/watch?v=aK9tOipNm0o

class FoodWasteRecordAdapter(val listener: FoodWasteRecordClickListenter) : RecyclerView.Adapter<FoodWasteRecordAdapter.MyViewHolder>() {

    var foodWasteRecord = ArrayList<FoodWasteRecord>()

    fun setFoodWasteRecordData(data: ArrayList<FoodWasteRecord>){
        this.foodWasteRecord = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodWasteRecordAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.food_waste_record_list_item,parent,false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(foodWasteRecord[position])
    }

    override fun getItemCount(): Int {
        return foodWasteRecord.size
    }


    class MyViewHolder(view: View, val listener: FoodWasteRecordClickListenter) : RecyclerView.ViewHolder(view) {

        val cookedProductName: TextView = view.findViewById<TextView>(R.id.cookedProductName)
        val wasteQuantity: TextView = view.findViewById<TextView>(R.id.wasteQuantity)
        val date: TextView = view.findViewById<TextView>(R.id.date)
        val deleteFoodWasteRecord: Button = view.findViewById<Button>(R.id.deleteFoodWasteRecord)
        fun bind(data: FoodWasteRecord) {
            cookedProductName.text = data.cooked_product_name
            wasteQuantity.text = data.waste_quantity.toString()
            date.text = data.timestamp.toString()


            deleteFoodWasteRecord.setOnClickListener {
                listener.onDeleteFoodWasteRecordClickListener(data)
            }
        }
    }


    interface FoodWasteRecordClickListenter{
        fun onDeleteFoodWasteRecordClickListener(foodWasteRecord: FoodWasteRecord)
    }
}