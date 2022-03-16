package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.DeliveryRecord



class DeliveryReportAdapter() : RecyclerView.Adapter<DeliveryReportAdapter.MyViewHolder>() {

    var deliveryRecord = emptyList<DeliveryRecord>()

    fun setDeliveryRecordData(data: List<DeliveryRecord>){
        this.deliveryRecord = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryReportAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.delivery_report_list_item,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(deliveryRecord[position])
    }

    override fun getItemCount(): Int {
        return deliveryRecord.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val productName: TextView = view.findViewById<TextView>(R.id.productName)
        val productSupplier: TextView = view.findViewById<TextView>(R.id.productSupplier)
        val quantity: TextView = view.findViewById<TextView>(R.id.quantity)
        val temperature: TextView = view.findViewById<TextView>(R.id.temperature)
        val date: TextView = view.findViewById<TextView>(R.id.date)
        fun bind(data: DeliveryRecord) {
            productName.text = data.product_name
            productSupplier.text = data.supplier
            quantity.text = data.quantity.toString()
            temperature.text = data.product_temperature.toString()
            date.text = data.timestamp.toString()



        }
    }



}