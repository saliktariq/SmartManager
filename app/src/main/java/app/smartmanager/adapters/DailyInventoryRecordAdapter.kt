package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.DailyInventoryRecord


// https://www.youtube.com/watch?v=aK9tOipNm0o

class DailyInventoryRecordAdapter(val listener: DailyInventoryRecordClickListenter) : RecyclerView.Adapter<DailyInventoryRecordAdapter.MyViewHolder>() {

    var dailyInventoryRecord = ArrayList<DailyInventoryRecord>()

    fun setDailyInventoryRecordData(data: ArrayList<DailyInventoryRecord>){
        this.dailyInventoryRecord = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyInventoryRecordAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.daily_inventory_record_list_item,parent,false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(dailyInventoryRecord[position])
    }

    override fun getItemCount(): Int {
        return dailyInventoryRecord.size
    }


    class MyViewHolder(view: View, val listener: DailyInventoryRecordClickListenter) : RecyclerView.ViewHolder(view) {

        val productName: TextView = view.findViewById<TextView>(R.id.productName)
        val quantity: TextView = view.findViewById<TextView>(R.id.quantity)
        val date: TextView = view.findViewById<TextView>(R.id.date)
        val deleteDailyInventoryRecord: Button = view.findViewById<Button>(R.id.deleteDailyInventoryRecord)
        fun bind(data: DailyInventoryRecord) {
            productName.text = data.product_name
            quantity.text = data.quantity.toString()
            date.text = data.timestamp.toString()


            deleteDailyInventoryRecord.setOnClickListener {
                listener.onDeleteDailyInventoryRecordClickListener(data)

            }


        }

    }


    interface DailyInventoryRecordClickListenter{
        fun onDeleteDailyInventoryRecordClickListener(dailyInventoryRecord: DailyInventoryRecord)

    }


}