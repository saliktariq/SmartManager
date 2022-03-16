package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.CleaningRecord
import app.smartmanager.datalayer.entity.CookedProductTemperatureRecord

class DonenessTestReportsAdapter: RecyclerView.Adapter<DonenessTestReportsAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cookedProductName: TextView = view.findViewById<TextView>(R.id.cookedProductName)
        val temperature: TextView = view.findViewById<TextView>(R.id.temperature)
        val date: TextView = view.findViewById<TextView>(R.id.date)

        fun bind(data: CookedProductTemperatureRecord){
            cookedProductName.text = data.cooked_product_name
            temperature.text = data.temperature.toString()
            date.text = data.timestamp.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DonenessTestReportsAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cooked_product_temperature_report_list_item,parent,false)
        return DonenessTestReportsAdapter.MyViewHolder(inflater)
    }

    //List to contain ChemicalList objects
    private var cookedProductTemperatureRecordList = emptyList<CookedProductTemperatureRecord>()

    override fun onBindViewHolder(holder: DonenessTestReportsAdapter.MyViewHolder, position: Int) {
        val currentRecord = cookedProductTemperatureRecordList[position]
        holder.bind(currentRecord)
    }

    fun setDataSet(donenessTestRecord: List<CookedProductTemperatureRecord>){
        this.cookedProductTemperatureRecordList = donenessTestRecord

        //Notify UI about dataset change
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
return cookedProductTemperatureRecordList.size
    }
}