package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.EquipmentTemperatureRecord



class EquipmentTemperatureReportsAdapter() : RecyclerView.Adapter<EquipmentTemperatureReportsAdapter.MyViewHolder>() {

    var equipmentTemperatureRecord = ArrayList<EquipmentTemperatureRecord>()

    fun setEquipmentTemperatureRecordData(data: ArrayList<EquipmentTemperatureRecord>){
        this.equipmentTemperatureRecord = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentTemperatureReportsAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.equipment_temperature_report_list_item,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(equipmentTemperatureRecord[position])
    }

    override fun getItemCount(): Int {
        return equipmentTemperatureRecord.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val equipmentName: TextView = view.findViewById<TextView>(R.id.equipmentName)
        val temperature: TextView = view.findViewById<TextView>(R.id.temperature)
        val date: TextView = view.findViewById<TextView>(R.id.date)
        fun bind(data: EquipmentTemperatureRecord) {
            equipmentName.text = data.equipment_name
            temperature.text = data.temperature.toString()
            date.text = data.timestamp.toString()



        }

    }
}