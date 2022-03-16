package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.CookingRecord



class CookingReportAdapter() : RecyclerView.Adapter<CookingReportAdapter.MyViewHolder>() {

    var cookingRecord = emptyList<CookingRecord>()

    fun setCookingRecordData(data: List<CookingRecord>){
        this.cookingRecord = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookingReportAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cooking_report_list_item,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(cookingRecord[position])
    }

    override fun getItemCount(): Int {
        return cookingRecord.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cookedProductName: TextView = view.findViewById<TextView>(R.id.cookedProductName)
        val quantity: TextView = view.findViewById<TextView>(R.id.quantity)
        val date: TextView = view.findViewById<TextView>(R.id.date)
        fun bind(data: CookingRecord) {
            cookedProductName.text = data.cooked_product_name
            quantity.text = data.quantity.toString()
            date.text = data.timestamp.toString()





        }

    }





}