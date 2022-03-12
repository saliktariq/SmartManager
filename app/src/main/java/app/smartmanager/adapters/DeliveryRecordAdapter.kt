package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.DeliveryRecord


// https://www.youtube.com/watch?v=aK9tOipNm0o

class DeliveryRecordAdapter(val listener: DeliveryRecordClickListenter) : RecyclerView.Adapter<DeliveryRecordAdapter.MyViewHolder>() {

    var deliveryRecord = ArrayList<DeliveryRecord>()

    fun setDeliveryRecordData(data: ArrayList<DeliveryRecord>){
        this.deliveryRecord = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryRecordAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.delivery_record_list_item,parent,false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(deliveryRecord[position])
    }

    override fun getItemCount(): Int {
        return deliveryRecord.size
    }


    class MyViewHolder(view: View, val listener: DeliveryRecordClickListenter) : RecyclerView.ViewHolder(view) {

        val productName: TextView = view.findViewById<TextView>(R.id.productName)
        val productSupplier: TextView = view.findViewById<TextView>(R.id.productSupplier)
        val quantity: TextView = view.findViewById<TextView>(R.id.quantity)
        val temperature: TextView = view.findViewById<TextView>(R.id.temperature)
        val date: TextView = view.findViewById<TextView>(R.id.date)
        val deleteDeliveryRecord: Button = view.findViewById<Button>(R.id.deleteDeliveryRecord)
        fun bind(data: DeliveryRecord) {
            productName.text = data.product_name
            productSupplier.text = data.supplier
            quantity.text = data.quantity.toString()
            temperature.text = data.product_temperature.toString()
            date.text = data.timestamp.toString()


            deleteDeliveryRecord.setOnClickListener {
                listener.onDeleteDeliveryRecordClickListener(data)
            }
        }
    }


    interface DeliveryRecordClickListenter{
        fun onDeleteDeliveryRecordClickListener(deliveryRecord: DeliveryRecord)
    }
}