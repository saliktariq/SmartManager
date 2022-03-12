package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.CookedProductTemperatureRecord


// https://www.youtube.com/watch?v=aK9tOipNm0o

class CookedProductTemperatureRecordAdapter(val listener: CookedProductTemperatureRecordClickListener) : RecyclerView.Adapter<CookedProductTemperatureRecordAdapter.MyViewHolder>() {

    var cookedProductTemperature = ArrayList<CookedProductTemperatureRecord>()

    fun setCookedProductTemperatureData(data: ArrayList<CookedProductTemperatureRecord>){
        this.cookedProductTemperature = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookedProductTemperatureRecordAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cooked_product_temperature_record_list_item,parent,false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(cookedProductTemperature[position])
    }

    override fun getItemCount(): Int {
        return cookedProductTemperature.size
    }


    class MyViewHolder(view: View, val listener: CookedProductTemperatureRecordClickListener) : RecyclerView.ViewHolder(view) {

        val cookedProductName: TextView = view.findViewById<TextView>(R.id.cookedProductName)
        val temperature: TextView = view.findViewById<TextView>(R.id.temperature)
        val date: TextView = view.findViewById<TextView>(R.id.date)
        val deleteCookedProductTemperatureRecord: Button = view.findViewById<Button>(R.id.deleteCookedProductTemperatureRecord)
        fun bind(data: CookedProductTemperatureRecord) {
            cookedProductName.text = data.cooked_product_name
            temperature.text = data.temperature.toString()
            date.text = data.timestamp.toString()

            deleteCookedProductTemperatureRecord.setOnClickListener {
                listener.onDeleteCookedProductTemperatureRecordClickListener(data)

            }


        }

    }


    interface CookedProductTemperatureRecordClickListener{
        fun onDeleteCookedProductTemperatureRecordClickListener(cookedProductTemperatureRecord: CookedProductTemperatureRecord)

    }


}