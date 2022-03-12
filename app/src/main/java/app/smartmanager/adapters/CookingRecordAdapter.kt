package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.CookingRecord


// https://www.youtube.com/watch?v=aK9tOipNm0o

class CookingRecordAdapter(val listener: CookingRecordClickListenter) : RecyclerView.Adapter<CookingRecordAdapter.MyViewHolder>() {

    var cookingRecord = ArrayList<CookingRecord>()

    fun setCookingRecordData(data: ArrayList<CookingRecord>){
        this.cookingRecord = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookingRecordAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cooking_record_list_item,parent,false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(cookingRecord[position])
    }

    override fun getItemCount(): Int {
        return cookingRecord.size
    }


    class MyViewHolder(view: View, val listener: CookingRecordClickListenter) : RecyclerView.ViewHolder(view) {

        val cookedProductName: TextView = view.findViewById<TextView>(R.id.cookedProductName)
        val quantity: TextView = view.findViewById<TextView>(R.id.quantity)
        val date: TextView = view.findViewById<TextView>(R.id.date)
        val deleteCookingRecord: Button = view.findViewById<Button>(R.id.deleteCookingRecord)
        fun bind(data: CookingRecord) {
            cookedProductName.text = data.cooked_product_name
            quantity.text = data.quantity.toString()
            date.text = data.timestamp.toString()


            deleteCookingRecord.setOnClickListener {
                listener.onDeleteCookingRecordClickListener(data)

            }


        }

    }


    interface CookingRecordClickListenter{
        fun onDeleteCookingRecordClickListener(cookingRecord: CookingRecord)

    }


}